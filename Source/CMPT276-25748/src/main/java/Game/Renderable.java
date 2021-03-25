package Game;

import java.awt.Graphics2D;
/**
 * Renderable class
 * A class that provides commonly used functions for other classes to implement over
 */
public interface Renderable {
    /**
     * Parent function for drawing images and objects. Used in instances of Board, Movable, and Interactable.
     * @param g2d the Graphics2D instance used to render images at a (x,y) coordinate
     */
    public void draw(Graphics2D g2d);
    /**
     * Parent function for checking whether the renderable object is supposed to be active at the current time.
     * Used in instances of Board, Movable, and Interactable.
     */
    public boolean isVisible();
}
