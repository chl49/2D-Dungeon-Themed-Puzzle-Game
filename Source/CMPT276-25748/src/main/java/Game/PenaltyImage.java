package Game;
 
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
 
/**
 * PenaltyImage class
 * This class will load the image at given position.
 */
public class PenaltyImage {
  
   private Image image;
 
   public void loadImages() {
       image = new ImageIcon("Source/CMPT276-25748/src/sprite/bomb.png").getImage();
   }
 
   
   /** 
    * @param g2d
    * @param xPos
    * @param yPos
    */
   public void draw(Graphics2D g2d, int xPos, int yPos)
   {
       Helper.drawImage(image, g2d, xPos, yPos);
   }
}