package Game;

import java.awt.Graphics2D;

public class Enemy extends Movable implements Renderable {

    EnemyAnimation render;

    public Enemy()
    {
        render = new EnemyAnimation();
        render.loadImages();
    }

    @Override
    public void draw(Graphics2D g2d) {

        var pos2D = Helper.get2Dpos(pos);
        render.draw(g2d, pos2D[0], pos2D[1]);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
    
}
