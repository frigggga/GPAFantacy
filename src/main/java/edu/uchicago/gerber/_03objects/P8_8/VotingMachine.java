package edu.uchicago.gerber._03objects.P8_8;

public class VotingMachine {
    private int democrats;
    private int republicans;

    public VotingMachine() {
        clear();
    }

    public void clear() {
        democrats = 0;
        republicans = 0;
    }

    public void voteDemocrat() {
        democrats++;
    }

    public void voteRepublican() {
        republicans++;
    }

    public int getDemocratTally() {
        return democrats;
    }

    public int getRepublicanTally() {
        return republicans;
    }

}

