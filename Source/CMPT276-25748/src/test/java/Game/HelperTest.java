package Game;

import java.awt.Graphics2D;
import java.awt.Image;


/**
 * HelperTest class
 * Receives and sends variables between GameManagerTest and BoardTest objects
 */
public class HelperTest {
    /**
     * returns the x position of the BoardTest object in GameManagerTest
     * @param position the location of the objcet
     * @return x position
     */
    public static int xPos(int position)
    {
        return GameManagerTestold.instance().getBoard().calcXPos(position);
    }
    /**
     * returns the y position of the BoardTest object in GameManagerTest
     * @param position the location of the objcet
     * @return y position
     */
    public static int yPos(int position)
    {
        return GameManagerTestold.instance().getBoard().calcYPos(position);
    }

    /**
     * returns the x and y position of the BoardTest object in GameManagerTest
     * @param position the location of the objcet
     * @return x and y position
     */
    public static int[] get2Dpos(int position)
    {
        int xPos = HelperTest.xPos(position);
        int yPos = HelperTest.yPos(position);

        return new int[]{xPos, yPos};
    }
     /**
     * returns the x and y position of the BoardTest object in GameManagerTest
     * @param movable the object
     * @return x and y position
     */
    public static int[] get2Dpos(Movable movable)
    {
        var pos2D = HelperTest.get2Dpos(movable.getPosition());
        return pos2D;
    }

    /**
     * returns a position given from a (x,y) coordinate
     * @param x
     * @param y
     * @return
     */
    public static int getPosFrom2D(int x, int y)
    {
        return GameManagerTestold.instance().getBoard().calcPosFrom2D(x, y);
    }

    /**
     * Moves a movable object from the current position to a new position with directions given from xDir and yDir
     * @param position the current position of the movable object
     * @param xDir the x-direction the object is moving towards
     * @param yDir the y-direction the object is moving towards
     * @return the new position of the object
     */
    public static int move(int position, int xDir, int yDir)
    {
        int newPos = position;
        int x = xPos(position);
        int y = yPos(position);

        x += xDir;
        y += yDir;

        int rowSize = GameManagerTestold.instance().getBoard().rowSize;
        int rowCount = GameManagerTestold.instance().getBoard().rowCount;

        if(x >= 0 && x < rowSize
            && y >= 0 && y < rowCount)
        {
            newPos = y * rowSize + x;
        }

        return newPos;
    }

    public static void drawImage(Image image, Graphics2D g2d, int xPos, int yPos)
    {
        GameManagerTestold.instance().drawImage(image, g2d, xPos, yPos);
    }
}
