package Game;
 
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
 
public class RewardImage {
  
   private Image image;
 
   public void loadImages() {
       image = new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown1.png").getImage();
   }
 
   public void draw(Graphics2D g2d, int xPos, int yPos)
   {
       Helper.drawImage(image, g2d, xPos, yPos);
       System.out.println("1");
   }
}
