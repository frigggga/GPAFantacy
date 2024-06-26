package edu.uchicago.gerber._08final.GPAFantacy.controller;



import edu.uchicago.gerber._08final.GPAFantacy.model.Movable;

import java.util.concurrent.LinkedBlockingDeque;



/**
 * Effectively a Queue that enqueues and dequeues Game Operations (add/remove).
 * enqueue() may be called by main and animation threads simultaneously, therefore we
 * use a data structure from the java.util.concurrent package.
 */
public class GameOpsQueue extends LinkedBlockingDeque<GameOp> {

    public void enqueue(Movable mov, GameOp.Action action) {
        addLast(new GameOp(mov, action));
    }

    public GameOp dequeue() {
        return removeFirst();
    }
}
