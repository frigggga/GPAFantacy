package edu.uchicago.gerber._08final.GPAFantacy.model;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public abstract class Tower extends Sprite {
    protected int row; //for grid integer use
    protected int col; //for grid integer use
    protected TowerLevel towerLevel;

    protected long lastAttack = 0; // last attack time in milliseconds
    private boolean bMoving = true;


    public Tower(int row, int col, int type) {
        super();
        this.row = row;
        this.col = col;
        switch(type) {
            default:
                this.towerLevel = TowerLevel.LEVEL11;
                break;
            case 2:
                this.towerLevel = TowerLevel.LEVEL21;
                break;
            case 3:
                this.towerLevel = TowerLevel.LEVEL31;
                break;
        }

        setTeam(Team.FRIEND);
        setCenter(new Point(Space.TS_WIDTH / 2 + col * Space.TS_WIDTH,
                Space.TS_HEIGHT/ 2 + row * Space.TS_HEIGHT));
        setRadius(Space.TS_WIDTH / 2);
    }
    



    public boolean towerReady() {
        return (System.currentTimeMillis() - lastAttack) >= getCooldown();
    }

    private long getCooldown() {
        return this.towerLevel.cooldown;
    }


    public List<Projectile> attack(List<Movable> enemyList) {
        List<Projectile> list = new ArrayList<>();
        if (!towerReady()) {
            return list;
        }
        lastAttack = System.currentTimeMillis();
        for (Movable enemy : enemyList) {
            Enemy castEnemy = (Enemy) enemy;
            int distance = (int) Math.sqrt(Math.pow(castEnemy.getRow() - this.row, 2)
                    + Math.pow(castEnemy.getCol() - this.col, 2));
            if (distance < this.towerLevel.range) {
                setOrientation((int) calculateOrientation(getCenter().x, getCenter().y, castEnemy.getCenter().x, castEnemy.getCenter().y));
                list.add(getNewProjectile(castEnemy));
                break;
            }
        }
        return list;
    }

    public static double calculateOrientation(double x1, double y1, double x2, double y2) {
        double angleRadians = Math.atan2(x2 - x1, y1 - y2);
        double angleDegrees = Math.toDegrees(angleRadians);
        if (angleDegrees < 0) {
            angleDegrees += 360;
        }
        return angleDegrees;
    }

    public abstract Projectile getNewProjectile(Enemy enemy);

    public void upgrade() {
        if(this.towerLevel.curLevel == 1){
            if(this.towerLevel.type == 1){
                this.towerLevel = towerLevel.LEVEL12;
            }else if(this.towerLevel.type == 2){
                this.towerLevel = towerLevel.LEVEL22;
            }else if(this.towerLevel.type == 3) {
                this.towerLevel = towerLevel.LEVEL32;
            }else {
                throw new RuntimeException("There is no next level");
            }
        }else if(this.towerLevel.curLevel == 2){
                if(this.towerLevel.type == 1){
                    this.towerLevel = towerLevel.LEVEL13;
                }else {
                    throw new RuntimeException("There is no next level");
                }
        }
    }
    public abstract String getName();

    public abstract int getType();

    public int getAttack() {
        return towerLevel.attack;}

    public void changeGridPosition(int xVal, int yVal){
        row = xVal / 50;
        col = yVal / 50;
    }

}
