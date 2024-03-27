package edu.uchicago.gerber._08final.GPAFantacy.model;

public enum TowerLevel {

    LEVEL11(1, 1, 100, 60, 200, 5, "level 1"),
    LEVEL12(2, 1, 100, 30, 150, 6, "level 2"),
    LEVEL13(3, 1, 100, 30, 100, 7, "level 3"),

    LEVEL21(1, 2, 200, 100, 150, 7, "level 1"),
    LEVEL22(2, 2, 200, 50, 50, 8, "level 2"),

    LEVEL31(1, 3, 300, 200, 350, 9, "level 1"),
    LEVEL32(2, 3, 300, 80, 150, 10, "level 2");

    final int curLevel;
    final int type;
    public final int upgradeCost;
    final int attack;
    final long cooldown;
    final double range;
    final String name;


    TowerLevel(int cL, int type, int uC, int att, int cd, double rg, String name){
        this.curLevel = cL;
        this.type = type;
        this.upgradeCost = uC;
        this.attack = att;
        this.cooldown = cd;
        this.range = rg;
        this.name = name;
    }

}
