package Game;

import java.awt.Graphics2D;

public class Enemy extends Movable implements Renderable {

    EnemyAnimation render;

    private int lastPos = 0;

    public Enemy()
    {
        render = new EnemyAnimation();
        render.loadImages();
    }

    @Override
    public void draw(Graphics2D g2d) {

        var pos2D = Helper.get2Dpos(pos);
        int xDir = pos2D[0] - Helper.xPos(lastPos);
        int yDir = pos2D[1] - Helper.yPos(lastPos);
        render.draw(g2d, xDir, yDir, pos2D[0], pos2D[1]);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
    @Override
    public void updatePosition() {

        if(nextPos != pos)
        {
            lastPos = pos;
        }

        super.updatePosition();
    }

    
}
