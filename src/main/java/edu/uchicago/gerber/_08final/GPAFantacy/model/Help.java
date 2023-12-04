package edu.uchicago.gerber._08final.GPAFantacy.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Help extends Tower {

    private final int HELP_IMAGE = 0;
    public Help(int row, int col) {
        super(row, col, 3);
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        //TODO: Change location
        rasterMap.put(HELP_IMAGE, loadGraphic("/imgs/game/img_help.png") );
        setRasterMap(rasterMap);
    }


    @Override
    public Projectile getNewProjectile(Enemy enemy) {
        return new Projectile(enemy, this, Color.BLUE, 12);
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public String getName() {
        return "Help from TA";
    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(HELP_IMAGE));
    }

}

