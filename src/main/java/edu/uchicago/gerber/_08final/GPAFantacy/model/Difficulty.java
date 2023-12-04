package edu.uchicago.gerber._08final.GPAFantacy.model;

public enum Difficulty {
    EASY(1, 1000, 200, 10),
    MEDIUM(2, 500, 150, 30),
    HARD(3, 300, 100, 60);

    private final int level;
    private final int initialGold;
    private final int health;
    private final int enemy;

    private Difficulty(int level, int gold, int health, int enemy){
        this.level = level;
        this.initialGold = gold;
        this.health = health;
        this.enemy = enemy;
    }

    public int getLevel() {
        return level;
    }

    public int getInitialGold() {
        return initialGold;
    }

    public int getHealth() {
        return health;
    }

    public int getEnemy() {
        return enemy;
    }
}
