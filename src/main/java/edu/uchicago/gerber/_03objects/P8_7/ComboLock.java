package edu.uchicago.gerber._03objects.P8_7;

public class ComboLock {
    private int s1;
    private int s2;
    private int s3;
    private int dial;
    private int turn;
    private int[] secret;

    public ComboLock(int secret1, int secret2, int secret3) {
        this.s1 = secret1;
        this.s2 = secret2;
        this.s3 = secret3;
        this.dial = 0;
        this.turn = 0;
        this.secret = new int[3];
    }

    public void reset() {
        this.secret = new int[3];
        this.dial = 0;
        this.turn = 0;
    }

    public void turnLeft(int ticks) {
        this.dial = (this.dial - ticks + 40) % 40;
        this.turn++;
        if(turn < 4){
            this.secret[this.turn - 1] = ticks;
        }
    }

    public void turnRight(int ticks) {
        this.dial = (this.dial + ticks) % 40;
        this.turn++;
        if(turn < 4){
            this.secret[this.turn - 1] = ticks;
        }
    }

    public boolean open() {
        if (this.turn == 3 && this.dial == s1-s2+s3 && this.secret[0] == s1 && this.secret[1] == s2 && this.secret[2] == s3) {
            reset();
            return true;
        }
        reset();
        return false;
    }


}

