package edu.uchicago.gerber._08final.GPAFantacy.controller;

import edu.uchicago.gerber._08final.GPAFantacy.model.*;
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

	private boolean gameEnd = false;
	private boolean hasSpawnEnemy = false;
	private boolean hasStarted = false;
	private Tower selectedTower = null;
	private Enemy boss = null;
	private String prompt = "";
	private int isPlacingTower = -1;
	private int nEnemiesReachMonument = 0;
	private int totalEnemy = 0;
	private int speed = 0;

	private int selectedRow;
	private int selectedCol;

	//this value is used to count the number of frames (full animation cycles) in the game
	private long frame;
	private long initTime = 0;

	private Monument monument;
	private Enemy thisEnemy = null;

	//lists containing our movables subdivided by team
	private final LinkedList<Movable> movProjectile = new LinkedList<>();
	private final LinkedList<Movable> movFriend = new LinkedList<>();
	private final LinkedList<Movable> movEnemy = new LinkedList<>();
	private final LinkedList<Movable> movFloater = new LinkedList<>();

	private List<Tower> towers = new ArrayList<>();

	//create board as a grid of target spaces
	private Space[][] grid = new Space[12][16];
	public static int[][] map = {
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

//the player can now place towers
	public void initGame() {
		monument = new Monument(Difficulty.EASY.getHealth());
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
		totalEnemy =  numberEnemies;
		speed = 20;
	}

	//officially start the game
	public void startGame() {
		spawnEnemies();
		towersAttack();
	}

	public void advanceLevel(int curLevel) {
		this.level = curLevel + 1;
		setScore(score + 1000);
		setPaused(false);
		hasSpawnEnemy = false;
		if (curLevel == 1) {
			currentGold += Difficulty.MEDIUM.getInitialGold();
			numberEnemies = Difficulty.MEDIUM.getEnemy();
			nEnemiesToKill = numberEnemies;
			nEnemiesReachMonument = 0;
			totalEnemy = numberEnemies;
			speed = 15;
		} else if (curLevel == 2) {
			currentGold += Difficulty.HARD.getInitialGold();
			numberEnemies = Difficulty.HARD.getEnemy();
			nEnemiesToKill = numberEnemies;
			nEnemiesReachMonument = 0;
			totalEnemy = numberEnemies;
			speed = 20;
		} else {
			//achieved the highest level, jump to game over screen
			setGameEnd(true);
		}
	}

	//set a grid with space object to draw map on panel
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
					grid[i][j] = new Space(i, j, TileType.PATH);
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
		map[row][col] = 3;
		selectedTower.changeGridPosition(xVal, yVal);
	}

//generate a list of enemies according to speed, until it has reached the bound of maximum eneny number set by the game difficulty
	public void spawnEnemies() {
		if (numberEnemies > 0 && frame % speed == 0) {
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
			hasSpawnEnemy = true;
			opsQueue.enqueue(toAdd, GameOp.Action.ADD);
			numberEnemies--;
		}
	}

//generate projectiles and set the orientation of the projectile to enemy
	public void towersAttack() {
		for (Tower tower : getTowers()) {
			if(movEnemy.size() > 0){
				for (Projectile projectile : tower.attack(movEnemy)) {
					opsQueue.enqueue(projectile, GameOp.Action.ADD);
				}
			}

		}
	}

	public void updateScore(int bounty){
		currentGold += bounty;
		Random r = new Random();
		score += bounty * 100 + r.nextInt(50);
		nEnemiesToKill--;
	}

	public void setTile(int row, int col){
		grid[row][col].setTileType(TileType.SELECTED);
	}

	public void updateMonumentHealth(int damage){
		curHealth -= damage;
	}


	//check if all enemies have reached the monument or has been killed
	public boolean checkAllEnemy(){
		if(!hasSpawnEnemy){
			return false;
		}else {
			for (Movable mov : movEnemy) {
				Enemy castEnemy = (Enemy) mov;
				if (hasSpawnEnemy && (!castEnemy.hasReachedMonument() || castEnemy.getCurHealth() > 0)) {
					return false;
				}

			}
			return true;
		}
	}


	public void incrementFrame(){
		//use of ternary expression to simplify the logic to one line
		frame = frame < Long.MAX_VALUE ? frame + 1 : 0;
	}

	public void clearAll(){
		movProjectile.clear();
		movEnemy.clear();
		movFloater.clear();
		movFriend.clear();
	}

	public boolean isGameOver() {
		return gameEnd;
	}

	public boolean hasStarted(){
		return hasStarted;
	}


	public boolean isLose(){
		return curHealth <= 0;
	}






}
