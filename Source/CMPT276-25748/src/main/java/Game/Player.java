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
        
        int xPos = Helper.rowPos(pos);
        int yPos = Helper.colPos(pos);

        int xDir = Helper.rowPos(nextPos) - xPos;
        int yDir = Helper.colPos(nextPos) - yPos;

        render.draw(g2d, xDir, yDir, xPos, yPos);
        
    }

    @Override
    public boolean isVisible() {
        return true;
    }
    
}
