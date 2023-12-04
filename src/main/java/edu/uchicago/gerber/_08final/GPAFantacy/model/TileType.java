package edu.uchicago.gerber._08final.GPAFantacy.model;

import java.awt.*;

public enum TileType {
    REGULAR(0, Color.GREEN, true, false),
    PATH(1, Color.WHITE, false, true),
    SPAWN_POINT(2, Color.RED, false, true),
    DEAD_TILE(3, Color.GRAY, false, false),
    MONUMENT(4, Color.ORANGE, false, false),
    SELECTED(-1, Color.YELLOW, false, false);

    private final int value;
    private final Color color;
    private final boolean canPlaceTower;
    private final boolean canWalk;

    private TileType(int value, Color color, boolean canPlaceTower, boolean canWalk) {
        this.value = value;
        this.color = color;
        this.canPlaceTower = canPlaceTower;
        this.canWalk = canWalk;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public boolean canPlaceTower() {
        return canPlaceTower;
    }

    public boolean canWalk() {
        return canWalk;
    }
}
