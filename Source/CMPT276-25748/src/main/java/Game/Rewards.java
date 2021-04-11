
package Game;
import java.awt.Graphics2D;
/**
 * Rewards class
 * This class will receive input for (position and score) and render the image
 * at given position.
 */
public class Rewards extends Interactable implements Renderable{
 
   RewardImage render;
 
   public Rewards(int inputPos, int inputScore)
   {
        position = inputPos;
        originalPos = inputPos;
        score = inputScore;
        isActive = true;

        render = new RewardImage();
        render.loadImages();
   }
 
   
   /** 
    * @param g2d
    */
   @Override
   public void draw(Graphics2D g2d) {
 
        var pos2D = Helper.get2Dpos(position);
        render.draw(g2d, pos2D[0], pos2D[1]);
   }
 
 
   
   /** 
    * @return boolean
    */
   @Override
   public boolean isVisible() {
        return isActive;
   }
  
}
