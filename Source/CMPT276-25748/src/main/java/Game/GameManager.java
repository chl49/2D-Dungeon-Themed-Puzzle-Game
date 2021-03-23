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
import java.util.ArrayList;
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
    
    private final int MoveDistance = BLOCK_SIZE;

    private int playerX, playerY;
    private int moveX, moveY;
    private int cooldown=30;  //TICK TIME
    private Timer timer;

    private static GameManager _instance = null;

    private Board board;
    private Player player;
    private ArrayList<Renderable> renderables = new ArrayList<Renderable>();

    public static GameManager instance()
    {
        if(_instance == null)
        {
            _instance = new GameManager();
            _instance.init();
        }

        return _instance;
    }

    private void init()
    {
        initTimer();
        initBoard();
        initRendering();
        initEntities();

        //TODO: init controls
    }

    private void initTimer() {
        timer = new Timer(30, this);
        timer.start();
    }

    private void initBoard() {

        addKeyListener(new TAdapter()); //TODO: move this to init controls

        createBoard();
    }

    private void initRendering()
    {
        d = new Dimension(600, 600);

        setFocusable(true);

        setBackground(Color.black);
    }

    private void initEntities()
    {
        player = new Player(this);
        renderables.add(player);

        //TODO: enemies and stuff
    }

    private void createBoard() {
        String gridString = "";
        int noOfRows = 0;
        int noOfColumns = 0;
        String contentsOfArray = "";
        try {
            board = new Board("Source/CMPT276-25748/src/resources/input.txt");
            Cell[] newCellArray = board.getCellArray();
            noOfRows = board.getNoOfRows();
            noOfColumns = board.getNoOfColumns();
            for (int i = 0; i < (noOfRows*noOfColumns); i++) {
                contentsOfArray += newCellArray[i].getCellChar();
            }
            System.out.println("Contents from the text file: " + board.getFileContent()
                + "\nNumber of Rows = " + Integer.toString(noOfRows)
                + "\nNumber of Columns = " + Integer.toString(noOfColumns)
                + "\nContents from the Cell Array: " + contentsOfArray
                + "\nTotal number of cells: " + (noOfRows*noOfColumns));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    

    @Override
    public void addNotify() {
        //START UP HERE
        super.addNotify();

        initGame();
    }

    

  /*   private void playGame(Graphics2D g2d) {
        moveplayer();
    } */

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

        for(var r : renderables)
        {
            if(r.isVisible())
                r.draw(g2d);
        }
        //doAnim();
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

    public Board getBoard()
    {
        return board;
    }
}
