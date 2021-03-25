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
import java.util.ArrayList;


public class GameManager extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Dimension d;
    private final int BLOCK_SIZE = 30;  //sprite size is 30x30 pixels
    private Timer timer;

    private static GameManager _instance = null;

    private Board board;
    private Player player;
    private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
    private ArrayList<Movable> movables = new ArrayList<Movable>();
    private Image winScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/win.png").getImage();
    private Image loseScreen = new ImageIcon("Source/CMPT276-25748/src/sprite/lose.png").getImage();
    private AIPathManager pathManager;
    private ScoreManager scoreManager;
    private boolean isDirty = false;
    private boolean inGame = true;
    private int screenSwitch = 0;
    private String score = "Score:";
    private String message = score+"0";
    private ArrayList<Interactable> interactable = new ArrayList<Interactable>();
    private ArrayList<Integer> goalPositions = new ArrayList<Integer>();
    private int requiredRewardsCount = 0;

    private boolean isDebug = false;

    

    public static GameManager instance()
    {
        if(_instance == null)
        {
            _instance = new GameManager();
        }

        return _instance;
    }

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

        Penalty penalty = new Penalty(50, 1);
        renderables.add(penalty);
        interactable.add(penalty);

        BonusReward bonusreward = new BonusReward(40, 1);
        renderables.add(bonusreward);
        interactable.add(bonusreward);

    }

    private void initControls() {
        addKeyListener(new TAdapter());
    }

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
          //fontMetrics.stringWidth(message)
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
                  System.out.println("screenError");
                  break;
              }
        }
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(isDirty)
        {
            updateGameLogic();
            isDirty = false;
        }
        
        updateMovables();
        
        repaint();
    }

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
                i.setActive(false);
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
                }

                if(i instanceof BonusReward)
                {
                    scoreManager.addBonusReward(i.getScore());
                }

                i.setActive(false);
            }
        }
    }

    //returns true if game should end
    private boolean checkGameConditions()
    {
        if(scoreManager.hasReachedRewardsGoal())
        {
            //win!
            screenSwitch=1;
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

    public Board getBoard()
    {
        return board;
    }

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
