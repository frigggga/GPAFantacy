package edu.uchicago.gerber._08final.GPAFantacy.model;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Boss extends Enemy {
    private final int BOSS_IMAGE = 0;

    public Boss() {
        super();
        this.attack = 200;
        this.speed = 1;
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(BOSS_IMAGE, loadGraphic("/imgs/game/boss.gif") );
        setRasterMap(rasterMap);
    }


    @Override
    public void setMaxHealth() {
        this.maxHealth = 800;
    }

    @Override
    public String getName(){
        return "Final Exam";
    }

    @Override
    public int getBounty() {
        return 0;
    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(BOSS_IMAGE));
    }
}