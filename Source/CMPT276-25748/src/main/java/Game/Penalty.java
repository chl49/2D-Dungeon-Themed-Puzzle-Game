package Game;
import java.awt.Graphics2D;
 
 
public class Penalty extends Interactable implements Renderable{
 
    PenaltyImage render;
 
   public Penalty()
   {
       render = new PenaltyImage();
       render.loadImages();
   }
 
   @Override
   public void draw(Graphics2D g2d) {
 
       var pos2D = Helper.get2Dpos(position);
       render.draw(g2d, pos2D[0],pos2D[1]);
   }
 
 
   @Override
   public boolean isVisible() {
       return true;
   }
  
}
