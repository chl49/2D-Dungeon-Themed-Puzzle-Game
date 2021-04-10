package Game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class
 * Receives and sends variables between GameManager and Board objects
 */
public class Helper {
    /**
     * returns the x position of the Board object in GameManager
     * @param position the location of the objcet
     * @return x position
     */
    public static int xPos(int position)
    {
        return GameManager.instance().getBoard().calcXPos(position);
    }
    /**
     * returns the y position of the Board object in GameManager
     * @param position the location of the objcet
     * @return y position
     */
    public static int yPos(int position)
    {
        return GameManager.instance().getBoard().calcYPos(position);
    }

    /**
     * returns the x and y position of the Board object in GameManager
     * @param position the location of the objcet
     * @return x and y position
     */
    public static int[] get2Dpos(int position)
    {
        int xPos = Helper.xPos(position);
        int yPos = Helper.yPos(position);

        return new int[]{xPos, yPos};
    }
     /**
     * returns the x and y position of the Board object in GameManager
     * @param movable the object
     * @return x and y position
     */
    public static int[] get2Dpos(Movable movable)
    {
        var pos2D = Helper.get2Dpos(movable.getPosition());
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
        return GameManager.instance().getBoard().calcPosFrom2D(x, y);
    }

    private static List<AbstractMap.SimpleEntry<Integer, Integer>> directions = Arrays.asList(
        new AbstractMap.SimpleEntry<Integer, Integer>(1, 0),        //right
        //new AbstractMap.SimpleEntry<Integer, Integer>(1, -1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(0, -1),       //up
        //new AbstractMap.SimpleEntry<Integer, Integer>(-1, -1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(-1, 0),       //left
        //new AbstractMap.SimpleEntry<Integer, Integer>(-1, 1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(0, 1)         //down
        //new AbstractMap.SimpleEntry<Integer, Integer>(1, 1)
        );

    public static List<AbstractMap.SimpleEntry<Integer, Integer>> getDirections()
    {
        return directions;
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

        int rowSize = GameManager.instance().getBoard().rowSize;
        int rowCount = GameManager.instance().getBoard().rowCount;

        if(x >= 0 && x < rowSize
            && y >= 0 && y < rowCount)
        {
            newPos = y * rowSize + x;
        }

        return newPos;
    }

    public static void drawImage(Image image, Graphics2D g2d, int xPos, int yPos)
    {
        GameManager.instance().drawImage(image, g2d, xPos, yPos);
    }
}
