package edu.uchicago.gerber._08final.GPAFantacy.model;


import edu.uchicago.gerber._08final.GPAFantacy.controller.CommandCenter;
import edu.uchicago.gerber._08final.mvc.controller.Sound;

import java.awt.*;
import java.util.LinkedList;

public abstract class Enemy extends Sprite {
    protected int curHealth;
    protected int attack;
    protected int maxHealth;
    protected double speed;
    protected boolean eliminated = false;
    protected static int[][] map = CommandCenter.getInstance().map;
    protected boolean hasReachedMonument = false;


    public abstract void setMaxHealth();

    protected static final double DEFAULT_HEIGHT = 50;
    protected static final double DEFAULT_WIDTH = 50;


    private int row; // in grid integers
    private int col; // in grid integers

    private boolean bountyCollected;



    public double getSpeed() {
        return speed;
    }
    public int getAttack() {
        return attack;
    }


    public Enemy() {
        super();
        setTeam(Team.ENEMY);
        setCenter(new Point(Space.TS_WIDTH / 2, Space.TS_WIDTH / 2)); // initial position on canvas, the spawn point
        setRadius(Space.TS_WIDTH / 2);
        setCoord(0, 0);
        setRadius(Space.TS_WIDTH / 2);
        setOrientation(0); //originally facing right
        setMaxHealth();
        curHealth = maxHealth;

    }
    public abstract String getName();



    public void setCoord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void receiveDamage(int damage) {
        curHealth -= damage;
        if (curHealth <= 0) {
            eliminated = true;
        }
    }

    public int getHealthPercentage(){
        double percent = this.curHealth / this.maxHealth;
        return (int) (percent * 50);
    }

    public boolean isDead() {
        return curHealth == 0;
    }

    public boolean hasReachedMonument(){
        return hasReachedMonument;
    }


    public abstract int getBounty();

    public int getBountyToCollect() {
        if (!bountyCollected) {
            bountyCollected = true;
            return getBounty();
        }
        return 0;
    }

    public void setReachedMonument(boolean b){
        hasReachedMonument = b;
    }

    public int getCurHealth(){
        return curHealth;
    }

    // if right column is a path, go right
    // if not, go up or down
    @Override
    public void move() {
        setDeltaY(0);
        setDeltaX(0);
        if(isDead() || hasReachedMonument()){
            setExpiry(1);
        }

        if(map[row][col + 1] == TileType.PATH.getValue()){ //right column has a path
            setDeltaX(speed);
        }else if(map[row + 1][col] == TileType.PATH.getValue()){ // path is below this path
            setDeltaY(speed);
        }else if(map[row - 1][col] == TileType.PATH.getValue()){ // up path
            setDeltaY(-speed);
        }else if(map[row][col - 1] == TileType.PATH.getValue()){
            setDeltaX(-speed);
        }
        double newXPos = getCenter().x + getDeltaX();
        double newYPos = getCenter().y + getDeltaY();
        setCenter(new Point((int) newXPos, (int) newYPos));
        setCoord(getCenter().y/50, getCenter().x/50);

    }

    @Override
    public void remove(LinkedList<Movable> list) {
        super.remove(list);
        CommandCenter.getInstance().updateScore(10);
        Sound.playSound("theme.wav");
    }

    @Override
    public void add(LinkedList<Movable> list) {
        super.remove(list);
        Sound.playSound("shieldup.wav");
    }
}
