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
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;
import java.util.ArrayList;

/**
* GameManager class
* Loads and supervises all activities needed for the game to function. 
* Handles draws and key inputs
*/
public class GameManager extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Dimension d;
    private final int BLOCK_SIZE = 30;  //sprite size is 30x30 pixels
    private Timer timer;
    private Random rand = new Random();

    private static GameManager _instance = null;

    private Board board;
    private Player player;
    private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
    private ArrayList<Movable> movables = new ArrayList<Movable>();
    private Image winScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/win.png").getImage();
    private Image loseScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/lose.png").getImage();
    private Image menuScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/titlemenu.png").getImage();
    private AIPathManager pathManager;
    private ScoreManager scoreManager;
    private boolean isDirty = false;
    private boolean inGame = false;
    private boolean validKey = false;
    private int screenSwitch = 0;
    private String start;
    private String score = "Score:";
    private String message = score+"0";
    private ArrayList<Interactable> interactable = new ArrayList<Interactable>();
    private ArrayList<Integer> goalPositions = new ArrayList<Integer>();
    private int requiredRewardsCount = 0;
    private int bonusValue = 3;
    private int penaltyValue = 4;
    private int rewardValue = 1;

    private boolean isDebug = false;

    

    public static GameManager instance()
    {
        if(_instance == null)
        {
            _instance = new GameManager();
        }

        return _instance;
    }
    /**
     * Initalize variables and class instances
     * 
     */
    private void init()
    {
        initTimer();
        initBoard();
        initRendering();

        if(isDebug)
        {
            initDebugEntities();
        }

        initControls();
        
        pathManager = new AIPathManager(player, board);
        scoreManager = new ScoreManager(requiredRewardsCount);
    }
   
    /**
     * create timer instance to schedule threads
     */
    private void initTimer() {
        timer = new Timer(30, this);
        timer.start();
        
    }
    
    private void initBoard() {
        createBoard();
    }

    private void initRendering()
    {
        d = new Dimension(600, 600);
        //d = new Dimension(board.rowSize, board.rowCount);

        setFocusable(true);

        setBackground(Color.black);
    }

    private void initDebugEntities()
    {
        player = new Player(16);
        renderables.add(player);
        movables.add(player);
 
        Enemy enemy = new Enemy(22);
        renderables.add(enemy);
        movables.add(enemy);

        Rewards reward = new Rewards(18, rewardValue);
        renderables.add(reward);
        interactable.add(reward);

        Rewards reward2 = new Rewards(200, rewardValue);
        renderables.add(reward2);
        interactable.add(reward2);

        Rewards reward3 = new Rewards(174, rewardValue);
        renderables.add(reward3);
        interactable.add(reward3);

        Rewards reward4 = new Rewards(34, rewardValue);
        renderables.add(reward4);
        interactable.add(reward4);

        Rewards reward5 = new Rewards(65, rewardValue);
        renderables.add(reward5);
        interactable.add(reward5);

        Penalty penalty = new Penalty(50, penaltyValue);
        renderables.add(penalty);
        interactable.add(penalty);

        BonusReward bonusreward = new BonusReward(40, 1);
        renderables.add(bonusreward);
        interactable.add(bonusreward);

    }

    private void initControls() {
        addKeyListener(new TAdapter());
    }
    /**
     * create Board class and insert objects into cells 
     */
    private void createBoard() {
    
        try {
            board = new Board("Source/CMPT276-25748/src/resources/input.txt");
            renderables.add(board);

            var cells = board.getCellArray();

            for(var c : cells)
            {
                //creates player, enemies, interactables
                //also finds goal location(s)
                initFromCell(c);
            }

            //see board output
            if(isDebug)
            {
                debugBoardOutput(board);
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void resetBoard() {
        board.resetCellArray();

        for(var m : movables)
        {
            m.setPosition(m.getOriginalPos());
        }
        for(var i : interactable)
        {
            i.setPosition(i.getOriginalPos());
        }

        // var cells = board.getCellArray();
        // for(var c : cells)
        // {
        //     //creates player, enemies, interactables
        //     //also finds goal location(s)
        //     resetFromCell(c);
        // }
        // try {
        //     board.resetCellArray();
        //      = new Board("Source/CMPT276-25748/src/resources/input.txt");
        //     var cells = board.getCellArray();

        //     for(var c : cells)
        //     {
        //         //creates player, enemies, interactables
        //         //also finds goal location(s)
        //         resetFromCell(c);
        //     }  
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }

    

    @Override
    public void addNotify() {
        //START UP HERE
        super.addNotify();

        init(); //init game
    }

    @Override
    public void paintComponent(Graphics g) {
        //DRAW LOOP
        super.paintComponent(g);

        doDrawing(g);
    }
    /**
     * draw all objects cast into Graphics
     * @param g Graphics object responsible for drawing
     */

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);
        if(inGame){
            for(var r : renderables)
          {
            if(r.isVisible())
                r.draw(g2d);
          }
          var small = new Font("Helvetica", Font.BOLD, 20);
          var fontMetrics = this.getFontMetrics(small);  
          g.setColor(Color.white);
          g.setFont(small);
          g.drawString(message, 10, 15);   
        }
        else{

            switch(screenSwitch){
                case 1:
                  Helper.drawImage(winScreen, g2d, 0, 0);
                  break;
                case 2:
                  Helper.drawImage(loseScreen, g2d, 0, 0);
                  break;
                default:
                  Helper.drawImage(menuScreen, g2d, 0, 0);
                  break;
            }
            if(screenSwitch==0){
                var small = new Font("Arial", Font.BOLD, 20);
                var fontMetrics = this.getFontMetrics(small);  
                g.setColor(Color.cyan);
                g.setFont(small);
                start="press 's' to play game";
                g.drawString(start, 120, 350);  
            }
        }
    }
    /**
     * directs specific actions for arrow key inputs
     */

    class TAdapter extends KeyAdapter {
        //KEY INPUTS

        @Override
        public void keyPressed(KeyEvent e) {

            int moveX = 0;
            int moveY = 0;
            int key = e.getKeyCode();
            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    moveX = -1;
                    moveY = 0;
                    validKey=true;
             } else if (key == KeyEvent.VK_RIGHT) {
                    moveX = 1;
                    moveY = 0;
                    validKey=true;
                } else if (key == KeyEvent.VK_UP) {
                    moveX = 0;
                    moveY = -1;
                    validKey=true;
              } else if (key == KeyEvent.VK_DOWN) {
                    moveX = 0;
                    moveY = 1;
                    validKey=true;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }

                if(player != null)
                {
                   player.setNextPosition(Helper.move(player.getPosition(), moveX, moveY));
                   isDirty = true;
                }
            }
            else {
                if (key == 's' || key == 'S') {
                    if(screenSwitch==0){
                        inGame = true;
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(isDirty && validKey)
        {
            updateGameLogic();
            isDirty = false;
            validKey = false;
        }
        
        updateMovables();
        
        repaint();
    }
    /**
     * update game's next action and check conditions 
     */

    private void updateGameLogic()
    {
        updateEnemyPathing();
        updateMovables();
        updateInteractions();

        //check win/lose conditions
        if(checkGameConditions())
        {
            //System.out.println("Game Ends");
            inGame=false;

            //game is over, handle it
        }
    }

    /**
     * update player and enemy's next position
     */
    private void updateMovables()
    {
        for(var m : movables)
        {
            Cell[] newCellArray = board.getCellArray();
            if(newCellArray[m.getNextPosition()].isBlocking())
            {
                m.setNextPosition(m.getPosition());
            }
            m.updatePosition();
        }
    }
    /**
     * pathManager determines the best move for the enemy
     */

    private void updateEnemyPathing()
    {
        for(var m : movables)
        {
            if(m instanceof Enemy)
            {
                pathManager.setNextPosition(m);
            }
        }
    }

    /**
     * check all object's interactable conditions
     */

    private void updateInteractions()
    {
        for(var i : interactable)
        {
            if(!i.isActive())
            {
                continue;
            }
            
        if (i instanceof BonusReward)
        {
            ((BonusReward)i).decreaseLife();
            if (((BonusReward)i).isExpired()){
                //i.setActive(false);
                Cell[] newCellArray = board.getCellArray();
                newCellArray[i.getPosition()].setCellChar('o');
                board.setCellArray(newCellArray);
                int newposition=rand.nextInt(225);
                while(newCellArray[newposition].getCellChar()!='o'){
                    System.out.println("BIG WALL HERE");
                    newposition=rand.nextInt(225);
                }
                newCellArray[newposition].setCellChar('b');
                i.setPosition(newposition);
                i.setActive(true);
            }
        }

            if(player.getPosition() == i.getPosition())
            {
                if(i instanceof Rewards)
                {
                    scoreManager.addRequiredReward(i.getScore());
                    System.out.println(scoreManager.getRequiredRewardsCollected());
                    System.out.println(scoreManager.hasReachedRewardsGoal());
                    System.out.println(scoreManager.hasReachedRewardsGoal());
                    message = score + String.valueOf(scoreManager.getTotalScore());
                }

                if(i instanceof Penalty)
                {
                    scoreManager.addPenalty(i.getScore());
                    message = score + String.valueOf(scoreManager.getTotalScore());
                }

                if(i instanceof BonusReward)
                {
                    scoreManager.addBonusReward(i.getScore());
                    message = score + String.valueOf(scoreManager.getTotalScore());
                }

                i.setActive(false);
            }
        }
    }



    private boolean hitGoal(){
        Cell[] newCellArray = board.getCellArray();
            if(newCellArray[player.getPosition()].getCellChar()=='f')
            {
                return true;
            }
            else{
                return false;
            }
    }

    /**
     * Checks whether player has met a winning or losing condition
     * @return boolean for whether game should end
     */

    private boolean checkGameConditions()
    {
        if(scoreManager.hasReachedRewardsGoal())
        {
            //win!
            board.updateMap();
            if(hitGoal()){
                screenSwitch=1;
                return true;
            }
        }
        if(scoreManager.getTotalScore()<0){
            //lose!
            screenSwitch=2;
            return true;
        }

        for(var m : movables)
        {
            if(m instanceof Enemy)
            {
                if(m.getPosition() == player.getPosition())
                {
                    //lose!
                    screenSwitch=2;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * returns the current board instance and it's map layout
     * @return the Board class
     */

    public Board getBoard()
    {
        return board;
    }

    /**
     * Initialize an object within a cell
     * @param cell a node containing a char, which will represent an object type to be created
     * @return cell object
     */

    public Object initFromCell(Cell cell)
    {
        switch (cell.cellChar) {
            
            case 'e':
            {
                var enemy = new Enemy(cell.pos);
                renderables.add(enemy);
                movables.add(enemy);
                return enemy;
            }
            case 'r':
            {
                var reward = new Rewards(cell.pos, rewardValue);
                renderables.add(reward);
                interactable.add(reward);
                requiredRewardsCount++;
                return reward;
            }
            case 'b':
            {
                var reward = new BonusReward(cell.pos, bonusValue);
                renderables.add(reward);
                interactable.add(reward);
                return reward;
            }
            case 'p':
            {
                var penalty = new Penalty(cell.pos, penaltyValue);
                renderables.add(penalty);
                interactable.add(penalty);
                return penalty;
            }
            case 'm':
            {
                //make sure this refernces GameManager.player, not a local variable
                player = new Player(cell.pos);
                renderables.add(player);
                movables.add(player);
                return player;
            }
            case 'f':
            {
                goalPositions.add(cell.pos);
            }
            default:
            {
                return null;
            }
        }
    }

    public Object resetFromCell(Cell cell)
    {
        cell.getPosition();
        switch (cell.cellChar) {
            
            case 'e':
            {
                var enemy = new Enemy(cell.pos);
                renderables.add(enemy);
                movables.add(enemy);
                return enemy;
            }
            case 'r':
            {
                var reward = new Rewards(cell.pos, 1);
                renderables.add(reward);
                interactable.add(reward);
                requiredRewardsCount++;
                return reward;
            }
            case 'b':
            {
                var reward = new BonusReward(cell.pos, 1);
                renderables.add(reward);
                interactable.add(reward);
                return reward;
            }
            case 'p':
            {
                var penalty = new Penalty(cell.pos, 1);
                renderables.add(penalty);
                interactable.add(penalty);
                return penalty;
            }
            case 'm':
            {
                //make sure this refernces GameManager.player, not a local variable
                player = new Player(cell.pos);
                renderables.add(player);
                movables.add(player);
                return player;
            }
            case 'f':
            {
                goalPositions.add(cell.pos);
            }
            default:
            {
                return null;
            }
        }
    }

    public void drawImage(Image image, Graphics2D g2d, int xPos, int yPos)
    {
        g2d.drawImage(image, xPos * BLOCK_SIZE, yPos * BLOCK_SIZE, this);
    }

    /**
     * function used for checking errors within the board class
     * @param board the Board class instance being Gameed
     */

    public void debugBoardOutput(Board board)
    {
        String gridString = "";
        Cell[] newCellArray = board.getCellArray();
        int noOfRows = board.getNoOfRows();
        int noOfColumns = board.getNoOfColumns();
        for (int i = 0; i < (noOfRows*noOfColumns); i++) {
            gridString += newCellArray[i].getCellChar();
        }
        System.out.println("Contents from the text file: "
            + "\nNumber of Rows = " + Integer.toString(noOfRows)
            + "\nNumber of Columns = " + Integer.toString(noOfColumns)
            + "\nContents from the Cell Array: " + gridString
            + "\nTotal number of cells: " + (noOfRows*noOfColumns));
    }
}
