package edu.uchicago.gerber._03objects.P8_8;

public class Driver {
    public static void main(String[] args) {
        VotingMachine v = new VotingMachine();

        v.voteDemocrat();
        v.voteDemocrat();
        v.voteDemocrat();
        v.voteRepublican();
        v.voteRepublican();
        v.voteDemocrat();
        v.voteDemocrat();

        System.out.println("Democrat Votes: " + v.getDemocratTally());
        System.out.println("Republican Votes: " + v.getRepublicanTally());

        v.clear();
        System.out.println("Machine state has now been cleared. ");

        System.out.println("Democrat Votes: " + v.getDemocratTally());
        System.out.println("Republican Votes: " + v.getRepublicanTally());
    }
}

