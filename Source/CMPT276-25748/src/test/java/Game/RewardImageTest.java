package Game;
 
import java.awt.Image;
import javax.swing.ImageIcon;


import java.awt.Graphics2D;
 
/**
 * RewardImage class
 * This class will load the image for reward in the given position.
 */
public class RewardImageTest {
  
   private Image image;
 
   public void loadImages() {
       image = new ImageIcon("Source/CMPT276-25748/src/sprite/reward.png").getImage();
   }
 
   
   /** 
    * @param g2d
    * @param xPos
    * @param yPos
    */
   public void draw(Graphics2D g2d, int xPos, int yPos)
   {
       HelperTest.drawImage(image, g2d, xPos, yPos);
   }
}
