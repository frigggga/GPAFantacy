package edu.uchicago.gerber._08final.GPAFantacy.model;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Practice extends Tower {
    private final int PRACTICE_IMAGE = 0;
    public Practice(int row, int col) {
        super(row, col, 1);
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        //TODO: Change location
        rasterMap.put(PRACTICE_IMAGE, loadGraphic("/imgs/game/img_practice.png") );
        setRasterMap(rasterMap);
    }


    @Override
    public Projectile getNewProjectile(Enemy enemy) {
        return new Projectile(enemy, this, Color.RED, 5);
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String getName() {
        return "Practice";
    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(PRACTICE_IMAGE));
    }
}
