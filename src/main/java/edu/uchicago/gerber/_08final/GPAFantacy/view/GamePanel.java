package edu.uchicago.gerber._08final.GPAFantacy.view;


import edu.uchicago.gerber._08final.GPAFantacy.controller.CommandCenter;
import edu.uchicago.gerber._08final.GPAFantacy.controller.Game;
import edu.uchicago.gerber._08final.GPAFantacy.model.Enemy;
import edu.uchicago.gerber._08final.GPAFantacy.model.Movable;
import edu.uchicago.gerber._08final.GPAFantacy.model.Space;
import edu.uchicago.gerber._08final.GPAFantacy.model.Tower;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import static edu.uchicago.gerber._08final.GPAFantacy.controller.CommandCenter.map;


public class GamePanel extends Panel implements MouseListener, MouseMotionListener {

    // ==============================================================
    // FIELDS
    // ==============================================================
    private final Font fontNormal = new Font("SansSerif", Font.PLAIN, 10);
    private final Font fontBig = new Font("SansSerif", Font.BOLD, 30);
    private FontMetrics fontMetrics;
    private int fontWidth;
    private int fontHeight;

    //used for double-buffering
    private Image imgOff;
    private Graphics grpOff;

    public int mouseX;				// Tracks X position of mouse events
    public int mouseY;				// Tracks Y position of mouse events
    public boolean mouseIsPressed = false;


    // ==============================================================
    // CONSTRUCTOR
    // ==============================================================

    public GamePanel(Dimension dim) {

        GameFrame gameFrame = new GameFrame();
        gameFrame.getContentPane().add(this);
        gameFrame.pack();
        initFontInfo();
        gameFrame.setSize(dim);
        //change the name of the game-frame to your game name
        gameFrame.setTitle("Game Base");
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        setFocusable(true);

        this.addMouseListener(this); 			// Listen to our own mouse events.
        this.addMouseMotionListener(this);	// Listen to mouse movements

    }


    // ==============================================================
    // METHODS
    // ==============================================================

    private void drawGameStatus(final Graphics graphics){

        graphics.setColor(Color.white);
        graphics.setFont(fontNormal);

        //draw score always
        graphics.drawString("Score :  " + CommandCenter.getInstance().getScore(), 20, 690);

        //draw the level upper-left corner always
        String levelText = "Level: " + CommandCenter.getInstance().getLevel();
        graphics.drawString(levelText, 20, 710); //upper-left corner

        String goldText = "Gold: " + CommandCenter.getInstance().getCurrentGold();
        graphics.drawString(goldText, 20, 730); //upper-left corner

        String statusText = "Number of Enemies Remaining: " + CommandCenter.getInstance().getNEnemiesToKill();
        graphics.drawString(statusText, 20, 750); //upper-left corner

        //build the status string array with possible messages in middle of screen
        if (CommandCenter.getInstance().checkAllEnemy())
        { CommandCenter.getInstance().setPrompt(" Advanced to the next level");}
        if(CommandCenter.getInstance().getPrompt()!= "") {
            displayTextOnScreen(graphics, CommandCenter.getInstance().getPrompt());
        }


    }

    private void drawNumFrame(Graphics g) {
        g.setColor(Color.white);
        g.setFont(fontNormal);
        g.drawString("FRAME :  " + CommandCenter.getInstance().getFrame(), fontWidth,
                Game.DIM.height  - (fontHeight + 22));

    }

    private void drawMonumentHealth(Graphics g) {

        int xVal = 650;
        int yVal = 350;

        //draw meter
        g.setColor(Color.GREEN);
        double percentage = CommandCenter.getInstance().getCurHealth() / CommandCenter.getInstance().getMonument().getMaxHealth();
        int width =(int) (percentage * 100);
        g.fillRect(xVal, yVal, width, 10);

        //draw gray box
        g.setColor(Color.DARK_GRAY);
        g.drawRect(xVal, yVal, 100, 10);

        g.setColor(Color.ORANGE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 15));
        g.drawString("Current GPA: " + 4.0 * percentage, xVal - 10, yVal - 30);
    }

    private void drawEnemyHealth(Graphics g){
        for(Movable movEnemy : CommandCenter.getInstance().getMovEnemy()){
            Enemy castEnemy = (Enemy) movEnemy;
            int xVal = castEnemy.getCenter().x;
            int yVal =castEnemy.getCenter().y;

            //draw meter
            g.setColor(Color.RED);
            int percentage = castEnemy.getHealthPercentage();
            g.fillRect(xVal, yVal - 20, percentage, 6);
            if(percentage > 0){
                g.setColor(Color.DARK_GRAY);
                g.drawRect(xVal, yVal - 20, 50, 6);

                g.setColor(Color.white);
                g.setFont(fontNormal);
                g.drawString(castEnemy.getName(), xVal, yVal- 30 );
            }
        }
    }




    @Override
    public void update(Graphics g) {

        // The following "off" vars are used for the off-screen double-buffered image.
        imgOff = createImage(Game.DIM.width, Game.DIM.height);
        //get its graphics context
        grpOff = imgOff.getGraphics();

        //Fill the off-screen image background with black.
        grpOff.setColor(Color.BLACK);
        grpOff.fillRect(0, 0, Game.DIM.width, Game.DIM.height);



        //this is used for development, you may remove drawNumFrame() in your final game.
        drawNumFrame(grpOff);
        drawMenu(grpOff);

        if (CommandCenter.getInstance().hasInitialized()) {


            moveDrawMovables(grpOff,
                    CommandCenter.getInstance().getMovProjectile(),
                    CommandCenter.getInstance().getMovFloater(),
                    CommandCenter.getInstance().getMovEnemy(),
                    CommandCenter.getInstance().getMovFriend());


            drawMonumentHealth(grpOff);
            drawEnemyHealth(grpOff);
            drawGameStatus(grpOff);


            if(CommandCenter.getInstance().getIsPlacingTower() > 0 && CommandCenter.getInstance().getSelectedTower() != null) {
                CommandCenter.getInstance().moveTower(mouseX, mouseY);
            }

        } else if (CommandCenter.getInstance().isPaused()) {

            displayTextOnScreen(grpOff, "Game Paused");

        }

        //after drawing all the movables or text on the offscreen-image, copy it in one fell-swoop to graphics context
        // of the game panel, and show it for ~40ms. If you attempt to draw sprites directly on the gamePanel, e.g.
        // without the use of a double-buffered off-screen image, you will see flickering.
        g.drawImage(imgOff, 0, 0, this);
    }


    //this method causes all sprites to move and draw themselves
    @SafeVarargs
    private final void moveDrawMovables(final Graphics g, List<Movable>... teams) {

        BiConsumer<Movable, Graphics> moveDraw = (mov, grp) -> {
            mov.move();
            mov.draw(grp);
        };


        Arrays.stream(teams) //Stream<List<Movable>>
                //we use flatMap to flatten the teams (List<Movable>[]) passed-in above into a single stream of Movables
                .flatMap(Collection::stream) //Stream<Movable>
                .forEach(m -> moveDraw.accept(m, g));


    }


    private void initFontInfo() {
        Graphics g = getGraphics();            // get the graphics context for the panel
        g.setFont(fontNormal);                        // take care of some simple font stuff
        fontMetrics = g.getFontMetrics();
        fontWidth = fontMetrics.getMaxAdvance();
        fontHeight = fontMetrics.getHeight();
        g.setFont(fontNormal);                    // set font info
    }


    private void drawMenu(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(800, 0, 300, 800);

        // draw score & life counters to menu bar
        g.setColor(Color.BLACK);
        g.setFont(fontNormal);
        g.drawString("Press 1 to Purchase Practice Questions: Cost: 100", 820, 150);				// cost for black hole towers
        g.drawString("Press 2 to Review Course Notes: Cost: 200", 820, 170);					// cost for sun towers
        g.drawString("Press 3 to Ask Help From TA or Professor: Cost: 300", 820, 190);

        g.drawString("Rules of the game: ", 820, 220);
        g.drawString("1. Use number keys{1, 2, 3} to purchase a tower, click a place\n", 820, 260);
        g.drawString("on the map to place towers at your desired position to stop enemies. \n" , 820, 280);
        g.drawString( "2. You earn gold for stopping enemies, \n", 820, 300);
        g.drawString( "use gold to purchase new towers\n" , 820, 320);
        g.drawString("3. When you are ready, press s key to start the game.", 820, 340);
        g.drawString("4. Press space key for any surprise. \n", 820, 360);
        g.drawString("5. If your base has no health you lose.\n", 820, 380);
        g.drawString(" If you killed all enemies you win.", 820, 400);

        g.setFont(fontBig);
        g.drawString("GPA Protection", 820, 50);					// writes title
        g.drawLine(820, 55, 1080, 55);								// underscore
        g.drawString("Towers", 820, 130);							// writes towers
        g.drawLine(820, 135, 1080, 135);

    }


    // This method draws some text to the middle of the screen
    public void displayTextOnScreen(final Graphics graphics, String prompt) {
        graphics.setColor(Color.RED);
        //AtomicInteger is safe to pass into a stream
        graphics.drawString(prompt, (Game.DIM.width / 2 - 200) ,
                Game.DIM.height - 100);
        int num = 100;
        while (num-- > 0){
            repaint();
        }


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        //place tower if detect keycode release and the current place is valid
        if(CommandCenter.getInstance().getIsPlacingTower() > 0 && CommandCenter.getInstance().canPlaceTower(mouseX, mouseY)){
            CommandCenter.getInstance().placeTower(mouseX, mouseY);
            CommandCenter.getInstance().setIsPlacingTower(-1);
            CommandCenter.getInstance().setSelectedTower(null);
        }
        mouseIsPressed = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseIsPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseIsPressed = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseIsPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();

        if (mouseX > 800) {
            mouseX = 1;
            //left-bounds reached
        } else if (mouseX < 1) {
            mouseX = 799;
            //bottom-bounds reached
        } else if (mouseY > 600) {
            mouseY = 1;
            //top-bounds reached
        } else if (mouseY < 0) {
            mouseY = 599;
            //in-bounds
        }
        mouseIsPressed = false;
    }
}
