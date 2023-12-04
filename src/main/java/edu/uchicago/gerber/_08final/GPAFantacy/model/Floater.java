package edu.uchicago.gerber._08final.GPAFantacy.model;


import edu.uchicago.gerber._08final.mvc.controller.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Floater extends Sprite {

    private Random r = new Random();
    public enum Surprise{HEALTH, GOLD, DAMAGE};
    Surprise type;

    public Floater() {

        setTeam(Team.FLOATER);

        //default values, all of which can be overridden in the extending concrete classes
        setExpiry(100);
        setRadius(50);
        //set random DeltaX
        setDeltaX(somePosNegValue(10));
        //set random DeltaY
        setDeltaY(somePosNegValue(10));
        //set random spin
        setSpin(somePosNegValue(10));

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        int randomSeed = r.nextInt(3);
        switch(randomSeed){
            default:
                rasterMap.put(0, loadGraphic("/imgs/game/surprise/health.png") );
                type = Surprise.HEALTH;
                break;
            case 1:
                rasterMap.put(0, loadGraphic("/imgs/game/surprise/gold.png") );
                type = Surprise.GOLD;
                break;
            case 2:
                rasterMap.put(0, loadGraphic("/imgs/game/surprise/gpt.png") );
                type = Surprise.DAMAGE;
                break;

        }
        setRasterMap(rasterMap);


    }


    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }

    public Surprise getType(){
        return type;
    }

    @Override
    public void add(LinkedList<Movable> list) {
        super.add(list);
        if (getExpiry() > 0) {
            Sound.playSound("nuke-up.wav");
        }
    }
}
