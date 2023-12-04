package edu.uchicago.gerber._08final.GPAFantacy.model;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Project extends Enemy {
    private final int PROJECT_IMAGE = 0;

    public Project() {
        super();
        this.speed = 4;
        this.attack = 10;
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        //TODO: Change location
        rasterMap.put(PROJECT_IMAGE, loadGraphic("/imgs/game/img_blue.png") );
        setRasterMap(rasterMap);
    }

    @Override
    public void setMaxHealth() {
        this.maxHealth = 150;
    }


    @Override
    public int getBounty() {
        return 30;
    }


    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(PROJECT_IMAGE));
    }
}



