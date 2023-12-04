package edu.uchicago.gerber._08final.GPAFantacy.controller;

import edu.uchicago.gerber._08final.GPAFantacy.model.*;
import edu.uchicago.gerber._08final.GPAFantacy.view.GamePanel;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;


// ===============================================
// == This Game class is the CONTROLLER
// ===============================================

public class Game implements Runnable, KeyListener {

    // ===============================================
    // FIELDS
    // ===============================================

    public static final Dimension DIM = new Dimension(1100, 800); //the dimension of the game.
    private final GamePanel gamePanel;
    //this is used throughout many classes.
    public static final Random R = new Random();

    public final static int ANIMATION_DELAY = 40; // milliseconds between frames

    public final static int FRAMES_PER_SECOND = 1000 / ANIMATION_DELAY;

    private final Thread animationThread;


    //key-codes
    private static final int
            PAUSE = 80, // p key
            QUIT = 81, // q key
            TOWER1 = 49, // 1 key, purchase tower1
            TOWER2 = 50, // 2 key, purchase tower 2
            TOWER3 = 51, // 3 key, purchase tower 3

    START = 83, // s key
            UPGRADE = 85, // u key
            SELECT = 32, // space key
            MUTE = 77; // m-key mute;


    private final Clip soundThrust;
    private final Clip soundBackground;


    // ===============================================
    // ==CONSTRUCTOR
    // ===============================================

    public Game() {

        gamePanel = new GamePanel(DIM);
        gamePanel.addKeyListener(this); //Game object implements KeyListener
        soundThrust = Sound.clipForLoopFactory("whitenoise.wav");
        soundBackground = Sound.clipForLoopFactory("music-background.wav");
        CommandCenter.getInstance().initGame();

        //fire up the animation thread
        animationThread = new Thread(this); // pass the animation thread a runnable object, the Game object
        animationThread.setDaemon(true);
        animationThread.start();

    }


    // ===============================================
    // ==METHODS
    // ===============================================

    public static void main(String[] args) {
        //typical Swing application start; we pass EventQueue a Runnable object.
        EventQueue.invokeLater(Game::new);
    }

    // Game implements runnable, and must have run method
    @Override
    public void run() {

        // lower animation thread's priority, thereby yielding to the 'Event Dispatch Thread' or EDT
        // thread which listens to keystrokes
        animationThread.setPriority(Thread.MIN_PRIORITY);

        // and get the current time
        long startTime = System.currentTimeMillis();


        // this thread animates the scene
        while (Thread.currentThread() == animationThread) {


            //this call will cause all movables to move() and draw() themselves every ~40ms
            // see GamePanel class for details
            gamePanel.update(gamePanel.getGraphics());

            checkCollisions();
            checkNewLevel();
//            checkFloaters();
            //this method will execute add() and remove() callbacks on Movable objects
            processGameOpsQueue();
            //keep track of the frame for development purposes
            CommandCenter.getInstance().incrementFrame();

            // surround the sleep() in a try/catch block
            // this simply controls delay time between
            // the frames of the animation
            try {
                // The total amount of time is guaranteed to be at least ANIMATION_DELAY long.  If processing (update)
                // between frames takes longer than ANIMATION_DELAY, then the difference between startTime -
                // System.currentTimeMillis() will be negative, then zero will be the sleep time
                startTime += ANIMATION_DELAY;

                Thread.sleep(Math.max(0,
                        startTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                // do nothing (bury the exception), and just continue, e.g. skip this frame -- no big deal
            }
        } // end while
    } // end run

//    private void checkFloaters() {
//        spawnNewWallFloater();
//        spawnShieldFloater();
//        spawnNukeFloater();
//    }

    //TODO: Change
    private void checkCollisions() {

        Point pntPrjCenter, pntEnemyCenter;
        int radPrj, radEnemy;
        for (Movable movEnemy : CommandCenter.getInstance().getMovEnemy()) {

            Point pntMonument = CommandCenter.getInstance().getMonument().getCenter();
            int radMonument = CommandCenter.getInstance().getMonument().getRadius();

            pntEnemyCenter = movEnemy.getCenter();
            radEnemy = movEnemy.getRadius();

            if (pntMonument.distance(pntEnemyCenter) < (radMonument + radEnemy)) {
                //enqueue the enemy
                CommandCenter.getInstance().getOpsQueue().enqueue(movEnemy, GameOp.Action.REMOVE);
                //update health bar of monument
                Enemy castEnemy = (Enemy) movEnemy;
                CommandCenter.getInstance().updateMonumentHealth(castEnemy.getAttack());
            }//end if

            for (Movable movPrj : CommandCenter.getInstance().getMovProjectile()) {

                pntPrjCenter = movPrj.getCenter();
                radPrj = movPrj.getRadius();

                //detect collision
                if (pntPrjCenter.distance(pntEnemyCenter) < (radPrj + radEnemy)) {
                    //enqueue the projectile
                    CommandCenter.getInstance().getOpsQueue().enqueue(movPrj, GameOp.Action.REMOVE);
                    //enqueue the foe TODO: change the health bar of each enemy

                }

            }//end inner for
        }//end outer for
    }


    //This method adds and removes movables to/from their respective linked-lists.
    private void processGameOpsQueue() {

        //deferred mutation: these operations are done AFTER we have completed our collision detection to avoid
        // mutating the movable linkedlists while iterating them above.
        while (!CommandCenter.getInstance().getOpsQueue().isEmpty()) {

            GameOp gameOp = CommandCenter.getInstance().getOpsQueue().dequeue();

            //given team, determine which linked-list this object will be added-to or removed-from
            LinkedList<Movable> list;
            Movable mov = gameOp.getMovable();
            switch (mov.getTeam()) {
                case PROJECTILE:
                    list = CommandCenter.getInstance().getMovProjectile();
                    break;
                case FRIEND:
                    list = CommandCenter.getInstance().getMovFriend();
                    break;
                case FLOATER:
                    list = CommandCenter.getInstance().getMovFloater();
                    break;
                case ENEMY:
                default:
                    list = CommandCenter.getInstance().getMovEnemy();
            }

            //pass the appropriate linked-list from above
            //this block will execute the add() or remove() callbacks in the Movable models.
            GameOp.Action action = gameOp.getAction();
            if (action == GameOp.Action.ADD)
                mov.add(list);
            else //REMOVE
                mov.remove(list);

        }//end while
    }


    //    private void spawnNewWallFloater() {
//
//        if (CommandCenter.getInstance().getFrame() % NewWallFloater.SPAWN_NEW_WALL_FLOATER == 0 && isBrickFree()) {
//            CommandCenter.getInstance().getOpsQueue().enqueue(new NewWallFloater(), GameOp.Action.ADD);
//        }
//    }
//
//    private void spawnShieldFloater() {
//
//        if (CommandCenter.getInstance().getFrame() % ShieldFloater.SPAWN_SHIELD_FLOATER == 0) {
//            CommandCenter.getInstance().getOpsQueue().enqueue(new ShieldFloater(), GameOp.Action.ADD);
//        }
//    }
//
//    private void spawnNukeFloater() {
//
//        if (CommandCenter.getInstance().getFrame() % NukeFloater.SPAWN_NUKE_FLOATER == 0) {
//            CommandCenter.getInstance().getOpsQueue().enqueue(new NukeFloater(), GameOp.Action.ADD);
//        }
//    }
//
//
//    //this method spawns new Large (0) Asteroids
//    private void spawnBigAsteroids(int num) {
//        while (num-- > 0) {
//            //Asteroids with size of zero are big
//            CommandCenter.getInstance().getOpsQueue().enqueue(new Asteroid(0), GameOp.Action.ADD);
//
//        }
//    }


    private void checkNewLevel() {
        if (CommandCenter.getInstance().getNEnemiesToKill() <= 0) {
            int oldLevel = CommandCenter.getInstance().getLevel();
            if (oldLevel == 3) {
                CommandCenter.getInstance().setGameEnd(true);
            } else {
                CommandCenter.getInstance().advanceLevel(oldLevel);
            }

        }
    }


    // Varargs for stopping looping-music-clips
    private static void stopLoopingSounds(Clip... clpClips) {
        Arrays.stream(clpClips).forEach(clip -> clip.stop());
    }

    // ===============================================
    // KEYLISTENER METHODS
    // ===============================================

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == START && CommandCenter.getInstance().getTowers().size() == 0) {
            CommandCenter.getInstance().setPrompt("Please purchase and place towers before you can start the game.");
            return;
        }if (keyCode == START && ! CommandCenter.getInstance().hasStarted()){
            CommandCenter.getInstance().setPrompt("Game Started");
            CommandCenter.getInstance().setHasStarted(true);
            CommandCenter.getInstance().startGame();
            return;
        }


        switch (keyCode) {
            case PAUSE:
                CommandCenter.getInstance().setPaused(!CommandCenter.getInstance().isPaused());
                if (CommandCenter.getInstance().isPaused()) stopLoopingSounds(soundBackground, soundThrust);
                break;
            case QUIT:
                System.exit(0);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //show the key-code in the console
        System.out.println(keyCode);

        switch (keyCode) {
            case UPGRADE:
                CommandCenter.getInstance().upgrade();
                break;

            case MUTE:
                CommandCenter.getInstance().setMuted(!CommandCenter.getInstance().isMuted());

                if (!CommandCenter.getInstance().isMuted()) {
                    stopLoopingSounds(soundBackground);
                } else {
                    soundBackground.loop(Clip.LOOP_CONTINUOUSLY);
                }
                break;
            case TOWER1:
                if (CommandCenter.getInstance().canPurchaseTower(1)) { // has enough money to purchase tower
                    CommandCenter.getInstance().setIsPlacingTower(1);
                    Practice selectedTower = new Practice(0,0);
                    CommandCenter.getInstance().setSelectedTower(selectedTower);
                    CommandCenter.getInstance().getOpsQueue().enqueue(selectedTower, GameOp.Action.ADD);
                }
                break;
            case TOWER2:
                if (CommandCenter.getInstance().canPurchaseTower(2)) { // has enough money to purchase tower
                    CommandCenter.getInstance().setIsPlacingTower(2);
                    Review selectedTower = new Review(0,0);
                    CommandCenter.getInstance().setSelectedTower(selectedTower);
                    CommandCenter.getInstance().getOpsQueue().enqueue(selectedTower, GameOp.Action.ADD);
                }
                break;
            case TOWER3:
                if (CommandCenter.getInstance().canPurchaseTower(3)) { // has enough money to purchase tower
                    CommandCenter.getInstance().setIsPlacingTower(3);
                    Help selectedTower = new Help(0,0);
                    CommandCenter.getInstance().setSelectedTower(selectedTower);
                    CommandCenter.getInstance().getOpsQueue().enqueue(selectedTower, GameOp.Action.ADD);
                }
                break;

            default:
                break;
        }

    }


    @Override
    // does nothing, but we need it b/c of KeyListener contract
    public void keyTyped(KeyEvent e) {
    }
}




