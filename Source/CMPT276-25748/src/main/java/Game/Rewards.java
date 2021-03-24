
package Game;
import java.awt.Graphics2D;
 
 
public class Rewards extends Interactable implements Renderable{
 
   RewardImage render;
 
   public Rewards(int inputPos, int inputScore)
   {
        position = inputPos;
        score = inputScore;
        isActive = true;

       render = new RewardImage();
       render.loadImages();
   }
 
   @Override
   public void draw(Graphics2D g2d) {
 
       var pos2D = Helper.get2Dpos(position);
       render.draw(g2d, pos2D[0], pos2D[1]);
   }
 
 
   @Override
   public boolean isVisible() {
       return isActive;
   }
  
}