package Game;
import java.awt.Graphics2D;
 
 
/**
 * Penalty class
 * This class will receive the (position and score) input and render the images.
 */
public class PenaltyTest extends Interactable implements Renderable{
 
    PenaltyImageTest render;

    public PenaltyTest(int inputPos, int inputScore)
    {
        position = inputPos;
        score = inputScore;
        isActive = true;

        render = new PenaltyImageTest();
        render.loadImages();
    }
 
    
    /** 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {

        var pos2D = HelperTest.get2Dpos(position);
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
