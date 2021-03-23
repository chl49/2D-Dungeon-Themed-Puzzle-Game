package Game;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Player extends Movable implements Renderable{

    private PlayerAnimation render;
    private ImageObserver imageObs;

    public Player(ImageObserver observer)
    {
        setImageObserver(observer);

        render = new PlayerAnimation();
        render.loadImages();
    }

    @Override
    public void setImageObserver(ImageObserver observer) {
        imageObs = observer;
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        int xPos = Helper.rowPos(pos);
        int yPos = Helper.colPos(pos);

        int xDir = Helper.rowPos(nextPos) - xPos;
        int yDir = Helper.colPos(nextPos) - yPos;

        render.draw(imageObs, g2d, xDir, yDir, xPos, yPos);
        
    }

    @Override
    public boolean isVisible() {
        return true;
    }
    
}
