package Game;

import java.awt.Graphics2D;

public class Wall extends Cell implements Renderable{

    private WallAnimation render;

    public Wall(int pos, char cellContent)
    {
        super(pos, cellContent);

        isBlocking = true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO Auto-generated method stub
        int xPos = Helper.rowPos(pos);
        int yPos = Helper.colPos(pos);

        render.draw(g2d, xPos, yPos);
        
    }

    @Override
    public boolean isVisible() {
        // TODO Auto-generated method stub
        return true;
    }

}
