package Game;

import java.awt.Graphics2D;
import java.awt.Image;

public class Helper {
    
    public static int rowPos(int position)
    {
        return GameManager.instance().getBoard().calcRowPos(position);
    }

    public static int colPos(int position)
    {
        return GameManager.instance().getBoard().calcColPos(position);
    }

    public static int move(int position, int xDir, int yDir)
    {
        int newPos = position;
        int rowPos = rowPos(position);
        int colPos = colPos(position);

        rowPos += xDir;
        colPos += yDir;

        int rowSize = GameManager.instance().getBoard().rowSize;
        int rowCount = GameManager.instance().getBoard().rowCount;

        if(rowPos >= 0 && rowPos < rowSize
            && colPos >= 0 && colPos < rowCount)
        {
            newPos = colPos * rowSize + rowPos;
        }

        return newPos;
    }

    public static void drawImage(Image image, Graphics2D g2d, int xPos, int yPos)
    {
        GameManager.instance().drawImage(image, g2d, xPos, yPos);
    }
}
