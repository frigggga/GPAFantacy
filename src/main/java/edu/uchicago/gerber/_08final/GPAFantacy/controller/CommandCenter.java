package edu.uchicago.gerber._08final.GPAFantacy.controller;

import edu.uchicago.gerber._08final.GPAFantacy.model.*;
import edu.uchicago.gerber._08final.mvc.model.Bullet;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//The CommandCenter is a singleton that manages the state of the game.
//the lombok @Data gives us automatic getters and setters on all members
@Data
public class CommandCenter {

	private int level;
	private long score = 0;
	private int currentGold;
	private int numberEnemies;
	private int nEnemiesToKill;
	private double curHealth;
	private boolean paused;
	private boolean muted;

	private boolean gameEnd = true;
	private boolean hasInitialized = false;
	private boolean hasStarted = false;
	private Tower selectedTower = null;
	private Enemy boss = null;
	private String prompt = "";
	private int isPlacingTower = -1;

	private int selectedRow;
	private int selectedCol;

	//this value is used to count the number of frames (full animation cycles) in the game
	private long frame;
	private long initTime = 0;

	private Monument monument = new Monument(Difficulty.EASY.getHealth());

	//lists containing our movables subdivided by team
	private final LinkedList<Movable> movProjectile = new LinkedList<>();
	private final LinkedList<Movable> movFriend = new LinkedList<>();
	private final LinkedList<Movable> movEnemy = new LinkedList<>();
	private final LinkedList<Movable> movFloater = new LinkedList<>();

	private List<Enemy> enemies = new ArrayList<>();
	private List<Tower> towers = new ArrayList<>();

	//create board as a grid of target spaces
	private Space[][] grid = new Space[12][16];
	private final int[][] map = {
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0},
			{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

	private final GameOpsQueue opsQueue = new GameOpsQueue();


	//singleton
	private static CommandCenter instance = null;

	// Constructor made private
	private CommandCenter() {
	}

	//this class maintains game state - make this a singleton.
	public static CommandCenter getInstance() {
		if (instance == null) {
			instance = new CommandCenter();
		}
		return instance;
	}


	public void initGame() {
		hasInitialized = true;
		clearAll();
		setLevel(1);
		setPaused(false);
		setScore(0);
		setGameEnd(false);
		currentGold = Difficulty.EASY.getInitialGold();
		curHealth = Difficulty.EASY.getHealth();
		numberEnemies = Difficulty.EASY.getEnemy();
		nEnemiesToKill = numberEnemies;
		setUpGrid();
		opsQueue.enqueue(monument, GameOp.Action.ADD);
		initTime = System.currentTimeMillis();
	}

	public void startGame() {
		while(hasStarted)
			if(!paused){
				spawnEnemies();
				towersAttack();
			}
	}

	public void advanceLevel(int curLevel) {
		clearAll();
		this.level = curLevel + 1;
		setPaused(false);
		if (curLevel == 1) {
			currentGold = Difficulty.MEDIUM.getInitialGold();
			curHealth = Difficulty.MEDIUM.getHealth();
			numberEnemies = Difficulty.MEDIUM.getEnemy();
			nEnemiesToKill = numberEnemies;
		} else if (curLevel == 2) {
			currentGold = Difficulty.HARD.getInitialGold();
			curHealth = Difficulty.HARD.getHealth();
			numberEnemies = Difficulty.HARD.getEnemy();
			nEnemiesToKill = numberEnemies;
		} else {
			//achieved the highest level, jump to game over screen
			setGameEnd(true);
		}
	}

	public void setUpGrid() {
		//set up grid before the game loads
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				//spawn point
				if (map[i][j] == TileType.SPAWN_POINT.getValue()) {
					grid[i][j] = new Space(i, j, TileType.SPAWN_POINT);
				} else if (map[i][j] == TileType.MONUMENT.getValue()) {
					grid[i][j] = new Space(i, j, TileType.MONUMENT);
				} else if (map[i][j] == TileType.PATH.getValue()) {
					grid[i][j] = new Space(i + 1, j + 1, TileType.PATH);
				} else {
					grid[i][j] = new Space(i, j, TileType.REGULAR);
				}
				opsQueue.enqueue(grid[i][j], GameOp.Action.ADD);
			}
		}

	}

	public boolean canPurchaseTower(int type) {
		if (currentGold < type * 100) {
			setPrompt("insufficient gold to purchase tower");
			return false;
		}
		return true;
	}

	public boolean canPlaceTower(int xVal, int yVal) {
		int col = xVal / 50;
		int row = yVal / 50;
		if ( row > 12 || col > 16 || grid[row][col].getTileType() != TileType.REGULAR) { // place tower only on regular tile
			setPrompt("You cannnot place tower at this place");
			return false;
		}
		return true;

	}

	public void moveTower(int xVal, int yVal) {
		selectedTower.setCenter(new Point(xVal, yVal));
	}

	public void placeTower(int xVal, int yVal) {
		int col = xVal / 50;
		int row = yVal / 50;
		towers.add(selectedTower);
		currentGold -= selectedTower.getTowerLevel().upgradeCost;
		grid[row][col].setTileType(TileType.DEAD_TILE); // already has a tower
		selectedTower.changeGridPosition(xVal, yVal);
	}

	//TODO: change the speed & enemy constructor
// else if(frameCounter % 5 == 0 && frameCounter >= 250)	// fast
//	 	{
//	 		enemies.add(new Asteroid(line.getStart()));
//	 		enemies.add(new Alien(line.getStart()));
//	 		enemies.add(new Comet(line.getStart()));
//	 	}
	public void spawnEnemies() {
		if (numberEnemies > 0 && frame % 10 == 0) {
			Enemy toAdd;
			if (numberEnemies == 1) {
				toAdd = new Boss();
				this.boss = toAdd;
			} else {
				Random r = new Random();
				int type = r.nextInt(4) + 1; //either 1, 2, or 3
				switch (type) {
					//homework
					default:
						toAdd = new Homework();
						break;
					// quiz
					case 2:
						toAdd = new Quiz();
						break;
					// project
					case 3:
						toAdd = new Project();
						break;
				}
			}
			enemies.add(toAdd);
			opsQueue.enqueue(toAdd, GameOp.Action.ADD);
			numberEnemies--;
		}
	}

//TODO: update enemies list by changing the isDead() function to return dead enemy
	public void towersAttack() {
		for (Tower tower : getTowers()) {
			for (Projectile projectile : tower.attack(getEnemies())) {
				opsQueue.enqueue(projectile, GameOp.Action.ADD);
				Enemy enemy = projectile.getEnemy();
				enemy.receiveDamage(tower.getAttack());
				if (enemy.isDead()) {
					currentGold += enemy.getBountyToCollect();
					score += enemy.getBountyToCollect() * 10;
					enemies.remove(enemy);
					opsQueue.enqueue(enemy, GameOp.Action.REMOVE);
					nEnemiesToKill--;
				}
			}
		}
	}

	public void setTile(int row, int col){
		grid[row][col].setTileType(TileType.SELECTED);
	}

	public int upgrade(){
		if(selectedTower != null){
			int goldNeeded = selectedTower.getTowerLevel().upgradeCost;
			if(currentGold >= goldNeeded){
				selectedTower.upgrade();
				currentGold -= goldNeeded;
				selectedTower = null;
				return 1;
			}else { // not enough gold
				setPrompt("Not enough gold to upgrade tower");
				return -1;
			}
		}else{ // tower not selected
			setPrompt("You need to select a tower to upgrade");
				return -2;
			}

	}

	public void updateMonumentHealth(int damage){
		curHealth -= damage;
	}


	public void incrementFrame(){
		//use of ternary expression to simplify the logic to one line
		frame = frame < Long.MAX_VALUE ? frame + 1 : 0;
	}

	private void clearAll(){
		movProjectile.clear();
		movEnemy.clear();
		movFloater.clear();
		movFriend.clear();
	}

	public boolean isGameOver() {
		return gameEnd;
	}

	public boolean hasInitialized(){
		return hasInitialized;
	}

	public boolean hasStarted(){
		return hasStarted;
	}


	public boolean isLose(){
		return curHealth <= 0;
	}






}
