package edu.uchicago.gerber._08final.GPAFantacy.model;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Quiz extends Enemy {

    private final int QUIZ_IMAGE = 0;

    public Quiz() {
        super();
        this.speed = 4;
        this.attack = 5;
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(QUIZ_IMAGE, loadGraphic("/imgs/game/img_yellow.png" ));
        setRasterMap(rasterMap);

    }

    @Override
    public void setMaxHealth() {
        this.maxHealth = 2000;
    }

    @Override
    public String getName(){
        return "Quiz";
    }

    @Override
    public int getBounty() {
        return 20;
    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(QUIZ_IMAGE));
    }
}



