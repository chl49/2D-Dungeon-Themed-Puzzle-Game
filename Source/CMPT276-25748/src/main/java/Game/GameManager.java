package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.BufferedReader;
import java.io.FileReader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameManager extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Dimension d;
    private final int BLOCK_SIZE = 30;
    private final int N_BLOCKS = 20; //20x20 grid perhaps
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE; //20*30 = 600 for Length
    private final int AnimationDelay = 5;
    private final int AnimationFrames = 4;
    private final int MoveDistance = BLOCK_SIZE;

    private int AnimCooldown = AnimationDelay;
    private int AnimPos = 0; //Animation Looper
    private Image playerUp1, playerLeft1, playerRight1, playerDown1;
    private Image playerUp2, playerLeft2, playerRight2, playerDown2;
    private Image playerUp3, playerDown3, playerLeft3, playerRight3;
    private Image playerUp4, playerDown4, playerLeft4, playerRight4;

    private int playerX, playerY;
    private int moveX, moveY;
    private int cooldown=30;  //TICK TIME
    private Timer timer;


    public GameManager() {

        loadImages();
        initVariables();
        initBoard();
    }

    private void initBoard() {
        // String gridString = "";
        // int noOfRows = 0;
        // int noOfColumns = 0;
        // String contentsOfArray = "";

        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
        // CREATE BOARD
        createBoard();
        //ADD BOARD BACKGROUND
    }

    private void createBoard() {
        String gridString = "";
        int noOfRows = 0;
        int noOfColumns = 0;
        String contentsOfArray = "";
        try {
            Board newBoard = new Board("Source/CMPT276-25748/src/resources/input.txt");
            Cell[] newCellArray = newBoard.getCellArray();
            noOfRows = newBoard.getNoOfRows();
            noOfColumns = newBoard.getNoOfColumns();
            for (int i = 0; i < (noOfRows*noOfColumns); i++) {
                contentsOfArray += newCellArray[i].getCellChar();
            }
            System.out.println("Contents from the text file: " + newBoard.getFileContent()
                + "\nNumber of Rows = " + Integer.toString(noOfRows)
                + "\nNumber of Columns = " + Integer.toString(noOfColumns)
                + "\nContents from the Cell Array: " + contentsOfArray
                + "\nTotal number of cells: " + (noOfRows*noOfColumns));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void initVariables() {
        d = new Dimension(600, 600);
        //ADD INTERACTABLES
        //ADD MOVABLES

        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        //START UP HERE
        super.addNotify();

        initGame();
    }

    private void doAnim() {

        AnimCooldown--;

        if (AnimCooldown <= 0) {
            AnimCooldown = AnimationDelay;
            AnimPos++;

            if (AnimPos == AnimationFrames) {
                AnimPos = 0;
            }
        }
    }

    private void playGame(Graphics2D g2d) {
        moveplayer();
        drawplayer(g2d);
    }

    private void moveplayer() {
        // CHECK BOARD CONSTRAINTS HERE
        // MOVE ENEMIES HERE OR CREATE NEW CLASS FOR MOVE ENEMIES IN playGame
        cooldown--;
        if(cooldown<=0){
            cooldown=30;
            //IF CONSTRAINT AT NEW LOCATION, CANCEL MOVE
            playerX = playerX + MoveDistance * moveX;
            playerY = playerY + MoveDistance * moveY;
        }
    }

    private void drawplayer(Graphics2D g2d) {

        if (moveX == -1) {
            drawplayerLeft(g2d);
        } else if (moveX == 1) {
            drawplayerRight(g2d);
        } else if (moveY == -1) {
            drawplayerUp(g2d);
        } else {
            drawplayerDown(g2d);
        }
    }

    private void drawplayerUp(Graphics2D g2d) {

        switch (AnimPos) {
            case 1:
                g2d.drawImage(playerUp2, playerX, playerY, this);
                break;
            case 2:
                g2d.drawImage(playerUp3, playerX, playerY, this);
                break;
            case 3:
                g2d.drawImage(playerUp4, playerX, playerY, this);
                break;
            default:
                g2d.drawImage(playerUp1, playerX, playerY, this);
                break;
        }
    }

    private void drawplayerDown(Graphics2D g2d) {

        switch (AnimPos) {
            case 1:
                g2d.drawImage(playerDown2, playerX, playerY, this);
                break;
            case 2:
                g2d.drawImage(playerDown3, playerX, playerY, this);
                break;
            case 3:
                g2d.drawImage(playerDown4, playerX, playerY, this);
                break;
            default:
                g2d.drawImage(playerDown1, playerX, playerY, this);
                break;
        }
    }

    private void drawplayerLeft(Graphics2D g2d) {

        switch (AnimPos) {
            case 1:
                g2d.drawImage(playerLeft2, playerX, playerY, this);
                break;
            case 2:
                g2d.drawImage(playerLeft3, playerX, playerY, this);
                break;
            case 3:
                g2d.drawImage(playerLeft4, playerX, playerY, this);
                break;
            default:
                g2d.drawImage(playerLeft1, playerX, playerY, this);
                break;
        }
    }

    private void drawplayerRight(Graphics2D g2d) {

        switch (AnimPos) {
            case 1:
                g2d.drawImage(playerRight2, playerX, playerY, this);
                break;
            case 2:
                g2d.drawImage(playerRight3, playerX, playerY, this);
                break;
            case 3:
                g2d.drawImage(playerRight4, playerX, playerY, this);
                break;
            default:
                g2d.drawImage(playerRight1, playerX, playerY, this);
                break;
        }
    }

    private void initGame() {
        //UI DISPLAY HERE
        initLevel();
    }

    private void initLevel() {

        // BOARD AND OBJECTS HERE

        continueLevel();
    }

    private void continueLevel() {

        //PLAYER AND MOVEABLE START HERE

        playerX = 7 * BLOCK_SIZE; //STARTING LOCATION
        playerY = 7 * BLOCK_SIZE; //STARTING LOCATION
        moveX = 0;
        moveY = 0;
    }

    private void loadImages() {
        //SPRITES 
        //ADD OTHER CHARACTER SPRITES HERE
        playerUp1 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup1.png").getImage();
        playerUp2 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup2.png").getImage();
        playerUp3 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup3.png").getImage();
        playerUp4 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup4.png").getImage();
        playerDown1 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown1.png").getImage();
        playerDown2 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown2.png").getImage();
        playerDown3 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown3.png").getImage();
        playerDown4 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown4.png").getImage();
        playerLeft1 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft1.png").getImage();
        playerLeft2 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft2.png").getImage();
        playerLeft3 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft3.png").getImage();
        playerLeft4 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft4.png").getImage();
        playerRight1 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright1.png").getImage();
        playerRight2 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright2.png").getImage();
        playerRight3 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright3.png").getImage();
        playerRight4 = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright4.png").getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        //DRAW LOOP
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);
        doAnim();
        playGame(g2d);
    }

    class TAdapter extends KeyAdapter {
        //KEY INPUTS

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                moveX = -1;
                moveY = 0;
            } else if (key == KeyEvent.VK_RIGHT) {
                moveX = 1;
                moveY = 0;
            } else if (key == KeyEvent.VK_UP) {
                moveX = 0;
                moveY = -1;
            } else if (key == KeyEvent.VK_DOWN) {
                moveX = 0;
                moveY = 1;
            } else if (key == KeyEvent.VK_PAUSE) {
                if (timer.isRunning()) {
                    timer.stop();
                } else {
                    timer.start();
                }
            }

            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}
