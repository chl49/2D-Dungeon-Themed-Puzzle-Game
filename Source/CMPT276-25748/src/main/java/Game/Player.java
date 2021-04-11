package Game;

import java.awt.Graphics2D;

/**
* Player class
* Player controlled object
*/
public class Player extends Movable implements Renderable{

    private PlayerAnimation render;

    private int lastPos = 0;

    public Player(int startingPos)
    {
        pos = startingPos;
        nextPos = startingPos;
        originalPos = startingPos;
        render = new PlayerAnimation();
        render.loadImages();
    }

    /**
    * Stores last position to determine look direction for the sprite/animation
    */
    @Override
    public void updatePosition() {

        if(nextPos != pos)
        {
            lastPos = pos;
        }

        super.updatePosition();
    }

    /**
    * Uses look direction to select appropriate sprite/animation
    *
    * @param    g2d reference to graphics
    */
    @Override
    public void draw(Graphics2D g2d) {
        
        int xPos = Helper.xPos(pos);
        int yPos = Helper.yPos(pos);

        int xDir = xPos - Helper.xPos(lastPos);
        int yDir = yPos - Helper.yPos(lastPos);

        render.draw(g2d, xDir, yDir, xPos, yPos);
        
    }

    @Override
    public boolean isVisible() {
        return true;
    }
    
}
