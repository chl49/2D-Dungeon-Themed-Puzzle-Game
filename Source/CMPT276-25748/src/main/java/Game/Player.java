package Game;

import java.awt.Graphics2D;

public class Player extends Movable implements Renderable{

    private PlayerAnimation render;

    public Player()
    {
        render = new PlayerAnimation();
        render.loadImages();
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        int xPos = Helper.xPos(pos);
        int yPos = Helper.yPos(pos);

        int xDir = Helper.xPos(nextPos) - xPos;
        int yDir = Helper.yPos(nextPos) - yPos;

        render.draw(g2d, xDir, yDir, xPos, yPos);
        
    }

    @Override
    public boolean isVisible() {
        return true;
    }
    
}
