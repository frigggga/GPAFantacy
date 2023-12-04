package edu.uchicago.gerber._08final.GPAFantacy.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Monument extends Sprite {

    private final int MONUMENT_IMAGE = 0;
    private int curHealth;
    private int maxHealth;

    
    public Monument(int health) {
        super();
        curHealth = health;
        maxHealth = health;
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(MONUMENT_IMAGE, loadGraphic("/imgs/game/monument.png") );
        setRasterMap(rasterMap);

        setRadius(100);
        setTeam(Team.FRIEND);
        setCenter(new Point(700, 450));
    }



    public int getMaxHealth() {
        return maxHealth;
    }

    public void receiveDamage(int damage) {
        curHealth -= damage;
    }

    public boolean isDead() {
        return curHealth <= 0;
    }
    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(MONUMENT_IMAGE));
    }
}

