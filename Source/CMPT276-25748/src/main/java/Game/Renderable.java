package Game;

import java.awt.Graphics2D;
import java.awt.image.*;

public interface Renderable {
    
    public void draw(Graphics2D g2d);
    public boolean isVisible();
}
