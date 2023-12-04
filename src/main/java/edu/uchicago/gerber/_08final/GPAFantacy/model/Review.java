package edu.uchicago.gerber._08final.GPAFantacy.model;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class Review extends Tower {

    private final int REVIEW_IMAGE = 0;
    public Review(int row, int col) {
        super(row, col, 2);
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(REVIEW_IMAGE, loadGraphic("/imgs/game/img_review.png") );
        setRasterMap(rasterMap);
    }


    @Override
    public Projectile getNewProjectile(Enemy enemy) {
        return new Projectile(enemy, this, Color.YELLOW, 10);
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String getName() {
        return "Review Notes";
    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(REVIEW_IMAGE));
    }

}
