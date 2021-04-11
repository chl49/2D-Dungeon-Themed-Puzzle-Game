package Game;
import java.awt.Graphics2D;
 
/**
 * Penalty class
 * This class will receive the (position and score) input and render the images.
 */
public class Penalty extends Interactable implements Renderable{
 
    PenaltyImage render;

    public Penalty(int inputPos, int inputScore)
    {
        position = inputPos;
        originalPos = inputPos;
        score = inputScore;
        isActive = true;

        render = new PenaltyImage();
        render.loadImages();
    }
 
    
    /** 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {

        var pos2D = Helper.get2Dpos(position);
        render.draw(g2d, pos2D[0],pos2D[1]);
    }
 
    
    /** 
     * @return boolean
     */
    @Override
    public boolean isVisible() {
        return isActive;
    }
}
