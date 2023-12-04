package edu.uchicago.gerber._08final.GPAFantacy.model;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Homework extends Enemy {

    private final int HOMEWORK_IMAGE = 0;

    public Homework() {
        super();
        this.speed = 8;
        this.attack = 2;
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(HOMEWORK_IMAGE, loadGraphic("/imgs/game/img_red.png") );
        setRasterMap(rasterMap);
    }


    @Override
    public int getBounty() {
        return 10;
    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(HOMEWORK_IMAGE));
    }

    @Override
    public String getName(){
        return "Homework";
    }


    @Override
    public void setMaxHealth() {
        this.maxHealth = 600;
    }

}



