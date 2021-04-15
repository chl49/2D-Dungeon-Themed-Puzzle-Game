package Game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

    private Board board;
    private Player player;
    private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
    private ArrayList<Movable> movables = new ArrayList<Movable>();
    private Image winScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/win.png").getImage();
    private Image loseScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/lose.png").getImage();
    private AIPathManager pathManager;
    private ScoreManager scoreManager;
    private boolean isDirty = false;
    private boolean inTest = true;
    private int screenSwitch = 0;
    private String score = "Score:";
    private String message = score+"0";
    private ArrayList<Interactable> interactable = new ArrayList<Interactable>();
    private ArrayList<Integer> goalPositions = new ArrayList<Integer>();
    private int requiredRewardsCount = 0;
    Graphics2D g2d;

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
     */
    @Before
    public void init()
    {
        //initTimer();
        initBoard();
        //initRendering();

        // if(isDebug)
        // {
        //     initDebugEntities();
        // }

        // initControls();
        pathManager = new AIPathManager(player, board);
        scoreManager = new ScoreManager(requiredRewardsCount);
        //assertNotNull(pathManager);
        //assertNotNull(scoreManager);
        //System.out.println("Test Completed: GameManager - init");

    }
   
    /**
     * Create timer instance to schedule threads
     */
    @Test
    public void initTimer() {
        timer = new Timer(30, this);
        timer.start();
        assertTrue(timer.isRunning());
        System.out.println("Test Completed: GameManager - initTimer");
        
    }
    
    private void initBoard() {
        createBoard();
    }

    /**
     * Creating a window in the app, tests whether we can change the 
     * component given focus from the default FocusTraversalPolicy to JPanel component
     */
    @Test
    public void initRendering()
    {
        assertTrue(isFocusable());
        System.out.println("Test Completed: GameManager - initRendering");
        d = new Dimension(600, 600);
        //d = new Dimension(BoardTest.rowSize, BoardTest.rowCount);

        setFocusable(true);
        assertTrue(isFocusable());
        System.out.println("Test Completed: GameManager - initRendering");

        setBackground(Color.black);
    }

    /**
     * Test to see if the placements of objects 
     * within the cells are created and at the correct positions
     */
    @Test
    public void initDebugEntities()
    {
        player = new Player(16);
        renderables.add(player);
        movables.add(player);

        assertEquals(player.getPosition(), 16);
        System.out.println("Player at: "
        + String.valueOf(player.getPosition()));
 
        Enemy enemy = new Enemy(22);
        renderables.add(enemy);
        movables.add(enemy);

        assertEquals(enemy.getPosition(), 22);
        System.out.println("Enemy at: "
        + String.valueOf(player.getPosition()));

        Rewards reward = new Rewards(18, 1);
        renderables.add(reward);
        interactable.add(reward);


        Rewards reward2 = new Rewards(200, 1);
        renderables.add(reward2);
        interactable.add(reward2);

        Rewards reward3 = new Rewards(174, 1);
        renderables.add(reward3);
        interactable.add(reward3);

        Rewards reward4 = new Rewards(34, 1);
        renderables.add(reward4);
        interactable.add(reward4);

        Rewards reward5 = new Rewards(65, 1);
        renderables.add(reward5);
        interactable.add(reward5);

        System.out.println("Rewards at: "
        + String.valueOf(reward.getPosition())+ ", "
        + String.valueOf(reward2.getPosition())+ ", "
        + String.valueOf(reward3.getPosition())+ ", "
        + String.valueOf(reward4.getPosition())+ ", "
        + String.valueOf(reward5.getPosition())
        );

        Penalty penalty = new Penalty(50, 1);
        renderables.add(penalty);
        interactable.add(penalty);

        System.out.println("Penalty at: "
        + String.valueOf(penalty.getPosition()));

        BonusReward bonusreward = new BonusReward(40, 1);
        renderables.add(bonusreward);
        interactable.add(bonusreward);

        System.out.println("BonusReward at: "
        + String.valueOf(bonusreward.getPosition()));

        System.out.println("Test Completed: GameManager - initDebugEntities");
    }

    /**
     * Test to see with we have successfully created key input receiver
     */
    @Test
    public void initControls() {
        TAdapter T = new TAdapter();
        addKeyListener(T);
        assertNotNull(T);
        System.out.println("Test Completed: GameManager - initControls");
    }

    /**
     * Create BoardTest class and insert objects into cells 
     */
    public void createBoard() {
    
        try {
            board = new Board("./src/resources/input.txt");
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
                debugBoardOutput();
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

        //init(); //init Test
    }

    @Override
    public void paintComponent(Graphics g) {
        //DRAW LOOP
        super.paintComponent(g);

        doDrawing(g);
    }
    
    /**
     * Draw all objects cast into Graphics
     * @param g Graphics object responsible for drawing
     */
    private void doDrawing(Graphics g) {

        g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);
        // if(inTest){
        //     for(var r : renderables)
        //   {
        //     if(r.isVisible())
        //         r.draw(g2d);
        //   }
        //   var small = new Font("Helvetica", Font.BOLD, 20);
        //   var fontMetrics = this.getFontMetrics(small);

        //   g.setColor(Color.white);
        //   g.setFont(small);
        //   //fontMetrics.stringWidth(message)
        //   g.drawString(message, 10, 15);
        // }
        // else{
        //     switch(screenSwitch){
        //         case 1:
        //           HelperTest.drawImage(winScreen, g2d, 0, 0);
        //           break;
        //         case 2:
        //           HelperTest.drawImage(loseScreen, g2d, 0, 0);
        //           break;
        //         default:
        //           System.out.println("screenError");
        //           break;
        //       }
        // }
    }

    /**
     * Directs specific actions for arrow key inputs
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
                player.setNextPosition(Helper.move(player.getPosition(), moveX, moveY));
                isDirty = true;
            }
        }
    }

    public int move(int position, int xDir, int yDir)
    {
        int newPos = position;
        int rowSize = board.rowSize;
        int rowCount = board.rowCount;

        int x = newPos%rowSize;
        int y = newPos/rowSize;

        x += xDir;
        y += yDir;
        
        if(x >= 0 && x < rowSize
            && y >= 0 && y < rowCount)
        {
            newPos = y * rowSize + x;
        }

        return newPos;
    }

    /**
     * Test to see if the arrow key works
     */
    @Test
    public void testPressKey()
    {
        int pos = player.getPosition();
        int x = pos%board.rowSize;
        int y = pos/board.rowSize;
        System.out.println("Initial position: " + x +" and " + y);
        
        //testing press down key
        player.setNextPosition(move(player.getPosition(), 0, 1));
        assertEquals(player.getNextPosition(), x+board.rowSize*(y+1));

        
        //testing press up key
        player.setNextPosition(move(player.getPosition(), 0, -1));
        assertEquals(player.getNextPosition(), x+board.rowSize*y);
        
        
        // test press right key
        player.setNextPosition(move(player.getPosition(), 1, 0));
        assertEquals(player.getNextPosition(), (x+1)+board.rowSize*y);
        
        
        // test press left key
        player.setNextPosition(move(player.getPosition(), -1, 0));
        assertEquals(player.getNextPosition(), (x-1)+board.rowSize*y);
         
        System.out.println("should back the orignal random position: " + x + " and " + y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(isDirty)
        {
            updateTestLogic();
            isDirty = false;
        }
        
        //updateMovables();
        
        repaint();
    }

    /**
     * Update Test's next action and check conditions 
     */
    @Test
    public void updateTestLogic()
    {
        //updateEnemyPathing();
        //updateMovables();
        //updateInteractions();
        //check win/lose conditions
        assertTrue(inTest);
        scoreManager.allRewardsCollected();
        assertEquals(scoreManager.getTotalScore(),requiredRewardsCount);
        assertTrue(scoreManager.hasReachedRewardsGoal());
        player.setPosition(217);
        assertTrue(hitGoal());
        if(checkTestConditions())
        {
            inTest=false;
        }
        assertFalse(inTest);
        inTest=true;
        scoreManager.negativeScore();
        if(checkTestConditions())
        {
            inTest=false;
        }
        assertFalse(inTest);
        inTest=true;
        for(var m : movables)
        {
            m.setNextPosition(77);
            m.updatePosition();
        }
        if(checkTestConditions())
        {
            inTest=false;
        }
        assertFalse(inTest);
        System.out.println("Test Completed: GameManager - updateTestLogic");
    }

    /**
     * Update player and enemy's next position
     */
    @Test
    public void updateMovables()
    {
        for(var m : movables)
        {
            Cell[] newCellArray = board.getCellArray();
            if(newCellArray[m.getNextPosition()].isBlocking())
            {
                m.setNextPosition(m.getPosition());
            }
            m.updatePosition();
            assertEquals(m.getNextPosition(),m.getPosition());

        }
        System.out.println("Test Completed: GameManager - updateMovables");
    }

    /**
     * PathManager determines the best move for the enemy
     */
    @Test
    public void updateEnemyPathing()
    {
        for(var m : movables)
        {
            if(m instanceof Enemy)
            {
                pathManager.setNextPosition(m);
                assertNotEquals(m.getNextPosition(), m.getPosition());
            }

        }
        System.out.println("Test Completed: GameManager - updateEnemyPathing");

    }

    /**
     * Check all object's interactable conditions
     */
    @Test
    public void updateInteractions()
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
                    i.setActive(false);
                }
                assertTrue(i.isActive());
                ((BonusReward)i).endLife();
                if (((BonusReward)i).isExpired()){
                    i.setActive(false);
                }
                assertFalse(i.isActive());
                ((BonusReward)i).setActive(true);
                if (((BonusReward)i).isExpired()){
                    i.setActive(false);
                }
                assertTrue(i.isActive());
            }
            int newPosition = i.getPosition();
            player.setPosition(newPosition);
            if(player.getPosition() == i.getPosition())
            {
                int oldScore = scoreManager.getTotalScore();
                if(i instanceof Rewards)
                {
                    scoreManager.addRequiredReward(i.getScore());
                    assertEquals(scoreManager.getTotalScore(),oldScore+i.getScore());
                    System.out.println("current score: "+ oldScore+ ", "
                    +"added reward: "+ i.getScore()+ ", "
                    +"new score: "+ scoreManager.getTotalScore());
                    message = score + String.valueOf(scoreManager.getTotalScore());
                }

                if(i instanceof Penalty)
                {
                    scoreManager.addPenalty(i.getScore());
                    assertEquals(scoreManager.getTotalScore(),oldScore-i.getScore());
                    System.out.println("current score: "+ oldScore+ ", "
                    +"added penalty: "+ i.getScore()+ ", "
                    +"new score: "+ scoreManager.getTotalScore());
                    message = score + String.valueOf(scoreManager.getTotalScore());
                }

                if(i instanceof BonusReward)
                {
                    scoreManager.addBonusReward(i.getScore());
                    assertEquals(scoreManager.getTotalScore(),oldScore+i.getScore());
                    System.out.println("current score: "+ oldScore+ ", "
                    +"added bonus: "+ i.getScore()+ ", "
                    +"new score: "+ scoreManager.getTotalScore());
                    message = score + String.valueOf(scoreManager.getTotalScore());
                }
                i.setActive(false);
                assertFalse(i.isActive());

            }
            System.out.println("Test Completed: GameManager - updateInteractions");
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
     * Returns the current BoardTest instance and it's map layout
     * @return the BoardTest class
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
                var reward = new Rewards(cell.pos, 1);
                renderables.add(reward);
                interactable.add(reward);
                requiredRewardsCount++;
                return reward;
            }
            case 'b':
            {
                var reward = new BonusReward(cell.pos, 3);
                renderables.add(reward);
                interactable.add(reward);
                return reward;
            }
            case 'p':
            {
                var penalty = new Penalty(cell.pos, 2);
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
     * Function used for checking errors within the BoardTest class
     */
    @Test
    public void debugBoardOutput() throws IOException
    {
        String gridString = "";
        Cell[] newCellArray = board.getCellArray();
        int noOfRows = board.getNoOfRows();
        int noOfColumns = board.getNoOfColumns();
        for (int i = 0; i < (noOfRows*noOfColumns); i++) {
        gridString += newCellArray[i].getCellChar();
        }
        assertEquals(noOfRows,15);
        assertEquals(noOfColumns,15);
        System.out.println("Contents from the text file: "
        + "\nNumber of Rows = " + Integer.toString(noOfRows)
        + "\nNumber of Columns = " + Integer.toString(noOfColumns)
        + "\nContents from the Cell Array: " + gridString
        + "\nTotal number of cells: " + (noOfRows*noOfColumns));
        System.out.println("Test Completed: GameManager - debugBoardOutput");
    }
}