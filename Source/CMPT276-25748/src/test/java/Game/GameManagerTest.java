package Game;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
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

import java.util.ArrayList;

/**
* GameManager class
* Loads and supervises all activities needed for the Test to function. 
* Handles draws and key inputs
*/
public class GameManagerTest extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Dimension d;
    private final int BLOCK_SIZE = 30;  //sprite size is 30x30 pixels
    private Timer timer;

    private static GameManagerTest _instance = null;

    private BoardTest board;
    private PlayerTest player;
    private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
    private ArrayList<Movable> movables = new ArrayList<Movable>();
    private Image winScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/win.png").getImage();
    private Image loseScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/lose.png").getImage();
    private AIPathManagerTest pathManager;
    private ScoreManager scoreManager;
    private boolean isDirty = false;
    private boolean inTest = true;
    private int screenSwitch = 0;
    private String score = "Score:";
    private String message = score+"0";
    private ArrayList<Interactable> interactable = new ArrayList<Interactable>();
    private ArrayList<Integer> goalPositions = new ArrayList<Integer>();
    private int requiredRewardsCount = 0;

    private boolean isDebug = false;

    public static GameManagerTest instance()
    {
        if(_instance == null)
        {
            _instance = new GameManagerTest();
        }

        return _instance;
    }
    /**
     * Initalize variables and class instances
     * 
     */

    @Test
    public void init()
    {
        initTimer();
        initBoard();
        initRendering();

        if(isDebug)
        {
            initDebugEntities();
        }

        initControls();
        assertFalse(isDebug);
        
        pathManager = new AIPathManagerTest(player, board);
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
        createBoardTest();
    }

    private void initRendering()
    {
        d = new Dimension(600, 600);
        //d = new Dimension(BoardTest.rowSize, BoardTest.rowCount);

        setFocusable(true);

        setBackground(Color.black);
    }

    private void initDebugEntities()
    {
        player = new PlayerTest(16);
        renderables.add(player);
        movables.add(player);
 
        EnemyTest enemy = new EnemyTest(22);
        renderables.add(enemy);
        movables.add(enemy);

        RewardsTest reward = new RewardsTest(18, 1);
        renderables.add(reward);
        interactable.add(reward);

        RewardsTest reward2 = new RewardsTest(200, 1);
        renderables.add(reward2);
        interactable.add(reward2);

        RewardsTest reward3 = new RewardsTest(174, 1);
        renderables.add(reward3);
        interactable.add(reward3);

        RewardsTest reward4 = new RewardsTest(34, 1);
        renderables.add(reward4);
        interactable.add(reward4);

        RewardsTest reward5 = new RewardsTest(65, 1);
        renderables.add(reward5);
        interactable.add(reward5);

        PenaltyTest penalty = new PenaltyTest(50, 1);
        renderables.add(penalty);
        interactable.add(penalty);

        BonusRewardTest bonusreward = new BonusRewardTest(40, 1);
        renderables.add(bonusreward);
        interactable.add(bonusreward);

    }

    private void initControls() {
        addKeyListener(new TAdapter());
    }
    /**
     * create BoardTest class and insert objects into cells 
     */
    private void createBoardTest() {
    
        try {
            board = new BoardTest("Source/CMPT276-25748/src/resources/input.txt");
            renderables.add(board);

            var cells = board.getCellArray();

            for(var c : cells)
            {
                //creates player, enemies, interactables
                //also finds goal location(s)
                initFromCell(c);
            }

            //see BoardTest output
            if(isDebug)
            {
                debugBoardOutput(board);
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    

    @Override
    public void addNotify() {
        //START UP HERE
        super.addNotify();

        init(); //init Test
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
        if(inTest){
            for(var r : renderables)
          {
            if(r.isVisible())
                r.draw(g2d);
          }
          var small = new Font("Helvetica", Font.BOLD, 20);
          var fontMetrics = this.getFontMetrics(small);

          g.setColor(Color.white);
          g.setFont(small);
          //fontMetrics.stringWidth(message)
          g.drawString(message, 10, 15);
        }
        else{
            switch(screenSwitch){
                case 1:
                  HelperTest.drawImage(winScreen, g2d, 0, 0);
                  break;
                case 2:
                  HelperTest.drawImage(loseScreen, g2d, 0, 0);
                  break;
                default:
                  System.out.println("screenError");
                  break;
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

            if(player != null)
            {
                player.setNextPosition(HelperTest.move(player.getPosition(), moveX, moveY));
                isDirty = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(isDirty)
        {
            updateTestLogic();
            isDirty = false;
        }
        
        updateMovables();
        
        repaint();
    }
    /**
     * update Test's next action and check conditions 
     */

    private void updateTestLogic()
    {
        updateEnemyPathing();
        updateMovables();
        updateInteractions();

        //check win/lose conditions
        if(checkTestConditions())
        {
            //System.out.println("Test Ends");
            inTest=false;

            //Test is over, handle it
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
            if(m instanceof EnemyTest)
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
            
        if (i instanceof BonusRewardTest)
        {
            ((BonusRewardTest)i).decreaseLife();
            if (((BonusRewardTest)i).isExpired()){
                i.setActive(false);
            }
        }

            if(player.getPosition() == i.getPosition())
            {
                if(i instanceof RewardsTest)
                {
                    scoreManager.addRequiredReward(i.getScore());
                    System.out.println(scoreManager.getRequiredRewardsCollected());
                    System.out.println(scoreManager.hasReachedRewardsGoal());
                    System.out.println(scoreManager.hasReachedRewardsGoal());
                    message = score + String.valueOf(scoreManager.getTotalScore());
                }

                if(i instanceof PenaltyTest)
                {
                    scoreManager.addPenalty(i.getScore());
                }

                if(i instanceof BonusRewardTest)
                {
                    scoreManager.addBonusReward(i.getScore());
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
     * @return boolean for whether Test should end
     */

    private boolean checkTestConditions()
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

        for(var m : movables)
        {
            if(m instanceof EnemyTest)
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
     * returns the current BoardTest instance and it's map layout
     * @return the BoardTest class
     */

    public BoardTest getBoard()
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
                var enemy = new EnemyTest(cell.pos);
                renderables.add(enemy);
                movables.add(enemy);
                return enemy;
            }
            case 'r':
            {
                var reward = new RewardsTest(cell.pos, 1);
                renderables.add(reward);
                interactable.add(reward);
                requiredRewardsCount++;
                return reward;
            }
            case 'b':
            {
                var reward = new BonusRewardTest(cell.pos, 1);
                renderables.add(reward);
                interactable.add(reward);
                return reward;
            }
            case 'p':
            {
                var penalty = new PenaltyTest(cell.pos, 1);
                renderables.add(penalty);
                interactable.add(penalty);
                return penalty;
            }
            case 'm':
            {
                //make sure this refernces GameManager.player, not a local variable
                player = new PlayerTest(cell.pos);
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
     * function used for checking errors within the BoardTest class
     * @param BoardTest the BoardTest class instance being tested
     */

    public void debugBoardOutput(BoardTest board)
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
