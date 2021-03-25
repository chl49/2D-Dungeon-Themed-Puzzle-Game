package Game;

import java.awt.Graphics2D;

public class Player extends Movable implements Renderable{

    private PlayerAnimation render;

    private int lastPos = 0;

    public Player()
    {
        render = new PlayerAnimation();
        render.loadImages();
    }

    @Override
    public void updatePosition() {

        if(nextPos != pos)
        {
            lastPos = pos;
        }

        super.updatePosition();
    }

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
