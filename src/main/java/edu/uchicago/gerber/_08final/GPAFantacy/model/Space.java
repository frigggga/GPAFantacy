package edu.uchicago.gerber._08final.GPAFantacy.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Space extends Sprite{
    TileType tileType;
    public static final int TS_HEIGHT= 50;
    public static final int TS_WIDTH = 50;
    private int nX;
    private int nY;

    public Space(int x, int y, TileType type) {
        super();
        this.nX = x;
        this.nY = y;
        this.tileType = type;

        ArrayList<Point> pntCs = new ArrayList<Point>();

        //simple square wall
        pntCs.add(new Point(1, 1));
        pntCs.add(new Point(1, -1));
        pntCs.add(new Point(-1, -1));
        pntCs.add(new Point(-1, 1));
        setColor(tileType.getColor());
        setCenter(new Point(Space.TS_WIDTH / 2 + y * Space.TS_WIDTH,
                Space.TS_HEIGHT/ 2 + x * Space.TS_HEIGHT));

        setRadius(25);
        setTeam(Team.FLOATER);
        setCartesians(pntCs.toArray(new Point[0]));

    }

    public TileType getTileType() {
        return tileType;
    }


    public int getnX() {
        return nX;
    }

    public int getnY() {
        return nY;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }


    public void setnX(int nX) {
        this.nX = nX;
    }

    public void setnY(int nY) {
        this.nY = nY;
    };

    @Override
    public void draw(Graphics g) {
        g.setColor(tileType.getColor());
        renderVector(g);
//        if(tileType == tileType.SPAWN_POINT){
//            Map<Integer, BufferedImage> rasterMap = new HashMap<>();
//            //TODO: Change location
//            rasterMap.put(0, loadGraphic("/imgs/game/point.png") );
//            setRasterMap(rasterMap);
//            renderRaster((Graphics2D) g, getRasterMap().get(0));
//        }
    }
}
