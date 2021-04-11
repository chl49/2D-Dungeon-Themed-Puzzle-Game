package Game;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;

/**
 * BonusRewardImage class
 * Loading the image and call helper to draw the image onboard
 */
public class BonusRewardImage {
    private Image image;
 
   public void loadImages() {
       image = new ImageIcon("Source/CMPT276-25748/src/sprite/bonusRewards.png").getImage();
   }
 
   
   /** 
    * Draw bases on given x,y positions
    * @param g2d
    * @param xPos
    * @param yPos
    */
   public void draw(Graphics2D g2d, int xPos, int yPos)
   {
       Helper.drawImage(image, g2d, xPos, yPos);
   }
    
}
