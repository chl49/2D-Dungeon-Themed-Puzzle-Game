package Game;

import java.awt.Graphics2D;
import java.awt.Image;

public class Helper {
    
    public static int xPos(int position)
    {
        return GameManager.instance().getBoard().calcXPos(position);
    }

    public static int yPos(int position)
    {
        return GameManager.instance().getBoard().calcYPos(position);
    }

    public static int[] get2Dpos(int position)
    {
        int xPos = Helper.xPos(position);
        int yPos = Helper.yPos(position);

        return new int[]{xPos, yPos};
    }

    public static int[] get2Dpos(Movable movable)
    {
        var pos2D = Helper.get2Dpos(movable.getPosition());
        return pos2D;
    }

    public static int getPosFrom2D(int x, int y)
    {
        return GameManager.instance().getBoard().calcPosFrom2D(x, y);
    }
    

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
