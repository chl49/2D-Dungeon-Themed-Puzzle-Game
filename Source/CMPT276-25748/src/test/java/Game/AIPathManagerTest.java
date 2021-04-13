package Game;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AIPathManagerTest {
    
    private AIPathManager pathManager;
    private Board board;
    private Player player;
    private Enemy enemy;

    @Before
    public void setup() throws IOException
    {
        board = new Board("./src/test/resources/test_board.txt");

        int x = (int)Math.floor(board.rowSize/2);
        int y = (int)Math.floor(board.rowCount/2);
        int pos = board.calcPosFrom2D(x, y);

        player = new Player(pos);   //middle
        enemy = new Enemy(pos + 1);
        pathManager = new AIPathManager(player, board);
    }

    @Test
    public void testOneStep()
    {
        //check one step away in each direction
        for(var dir : Helper.getDirections())
        {
            int xPos = board.calcXPos(player.getPosition());
            int yPos = board.calcYPos(player.getPosition());
            
            xPos += dir.getKey();
            yPos += dir.getValue();

            enemy.setPosition(board.calcPosFrom2D(xPos, yPos));
            var nextPos = pathManager.getNextPos(enemy.getPosition());

            assertTrue(nextPos == player.getNextPosition());    //aim for player's next position, which is same as position initially
        }

        System.out.println("Test Completed: AIPathManager - One Step");
    }

    @Test
    public void testRandom()
    {
        for(int i = 0; i < 10; ++i)
        {
            //account for surrounding walls
            int x = (int)Math.floor(Math.random() * (board.rowSize - 2) + 1);   
            int y = (int)Math.floor(Math.random() * (board.rowCount - 2) + 1);
            int pos = board.calcPosFrom2D(x, y);
            player.setPosition(pos);

            System.out.println("Checking: Player: " + x + " " + y);

            x = (int)Math.floor(Math.random() * (board.rowSize - 2) + 1);   
            y = (int)Math.floor(Math.random() * (board.rowCount - 2) + 1);
            pos = board.calcPosFrom2D(x, y);

            enemy.setPosition(pos);

            System.out.println("Checking: Enemy: " + x + " " + y);

            assertTrue(findPath(player, enemy));
        }

        System.out.println("Test Completed: AIPathManager - Random");
    }

    @Test
    public void testManualCase()
    {
        //account for surrounding walls
        int x = 12;   
        int y = 3;
        int pos = board.calcPosFrom2D(x, y);
        player.setPosition(pos);

        System.out.println("Checking: Player: " + x + " " + y);

        x = 13;   
        y = 13;
        pos = board.calcPosFrom2D(x, y);

        enemy.setPosition(pos);

        System.out.println("Checking: Enemy: " + x + " " + y);
        
        assertTrue(findPath(player, enemy));
        
        System.out.println("Test Completed: AIPathManager - Manual Case");
    }

    @Test
    public void testObstacle() throws IOException
    {
        board = new Board("./src/test/resources/test_board_blocking.txt");

        player = new Player(board.calcPosFrom2D(7, 2));   //above the wall
        enemy = new Enemy(board.calcPosFrom2D(7, 10));      //below the wall
        pathManager = new AIPathManager(player, board);

        assertTrue(findPath(player, enemy));
        
        System.out.println("Test Completed: AIPathManager - Obstacle Case");
    }

    private boolean findPath(Player player, Enemy enemy)
    {
        var totalSize = (board.rowSize - 2) * (board.rowCount - 2);

        for(int j = 0; j < totalSize - 1; ++j)
        {
            var nextPos = pathManager.getNextPos(enemy.getPosition());

            if(nextPos == player.getNextPosition())
            {
                System.out.println("Found in: " + j);
                return true;
            }
            else
            {
                enemy.setPosition(nextPos);
            }
        }

        return false;
    }

}
