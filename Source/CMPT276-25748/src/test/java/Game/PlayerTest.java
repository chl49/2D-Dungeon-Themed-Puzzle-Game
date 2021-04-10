package Game;

import java.awt.Graphics2D;

/**
* Player class
* Player controlled object
*/
public class PlayerTest extends Movable implements Renderable{

    private PlayerAnimationTest render;

    private int lastPos = 0;

    public PlayerTest(int startingPos)
    {
        pos = startingPos;

        render = new PlayerAnimationTest();
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
        
        int xPos = HelperTest.xPos(pos);
        int yPos = HelperTest.yPos(pos);

        int xDir = xPos - HelperTest.xPos(lastPos);
        int yDir = yPos - HelperTest.yPos(lastPos);

        render.draw(g2d, xDir, yDir, xPos, yPos);
        
    }

    @Override
    public boolean isVisible() {
        return true;
    }
    
}
