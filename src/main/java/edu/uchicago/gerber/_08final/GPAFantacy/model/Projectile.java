package edu.uchicago.gerber._08final.GPAFantacy.model;


import edu.uchicago.gerber._08final.GPAFantacy.controller.CommandCenter;
import edu.uchicago.gerber._08final.GPAFantacy.controller.Game;
import edu.uchicago.gerber._08final.GPAFantacy.controller.GameOp;

import java.awt.*;

public class Projectile extends Sprite {
    private Enemy enemy;
    private Tower tower;
    private int startRow;
    private int startCol;
    private int type;

    public Projectile(Enemy enemy, Tower tower, Color color, double radius) {
        super();
        this.startCol = tower.getCol();
        this.startRow = tower.getRow();
        this.enemy = enemy;
        this.tower = tower;
        this.type = tower.getType();

        setExpiry(20);
        setTeam(Team.PROJECTILE);
        setColor(color);
        setOrientation((int) calculateOrientation(tower.getRow(), tower.getCol(), enemy.getRow(), enemy.getCol()));
        setCenter(new Point(Space.TS_WIDTH / 2 + startRow * Space.TS_WIDTH, Space.TS_HEIGHT/ 2 + startCol * Space.TS_HEIGHT));
        setRadius((int) radius);


    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public Tower getTower() {
        return tower;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double calculateOrientation(int ax, int ay, int bx, int by) {
        // Calculate the angle in radians
        double angleRadians = Math.atan2(by - ay, bx - ax);

        // Convert radians to degrees
        double angleDegrees = Math.toDegrees(angleRadians);

        // Normalize the angle to the range 0-359
        if (angleDegrees < 0) {
            angleDegrees += 360;
        }

        return angleDegrees;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(getCenter().x, getCenter().y, getRadius(), getRadius());
    }
    @Override
    public void move(){
        if (getCenter().x > Game.DIM.width || getCenter().x < 0 || getCenter().y > Game.DIM.height || getCenter().y < 0) {
            setExpiry(1);
        } else {
            double newXPos = getCenter().x + getDeltaX();
            double newYPos = getCenter().y + getDeltaY();
            setCenter(new Point((int) newXPos, (int) newYPos));
        }

        //expire (decrement expiry) on short-lived objects only
        //the default value of expiry is zero, so this block will only apply to expiring sprites
        super.expire();

    }
}
