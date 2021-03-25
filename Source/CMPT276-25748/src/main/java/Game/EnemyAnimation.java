package Game;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;

/**
* EnemyAnimation class
* Rendering component of the Enemy class
*/
public class EnemyAnimation {
    
    private Image upImage;
    private Image downImage;
    private Image leftImage;
    private Image rightImage;
    private Image image;

    public void loadImages() {
        //image = new ImageIcon("Source/CMPT276-25748/src/sprite/enemy1.png").getImage();
        upImage = new ImageIcon("Source/CMPT276-25748/src/sprite/ghostup.png").getImage();
        downImage = new ImageIcon("Source/CMPT276-25748/src/sprite/ghostdown.png").getImage();
        leftImage = new ImageIcon("Source/CMPT276-25748/src/sprite/ghostleft.png").getImage();
        rightImage = new ImageIcon("Source/CMPT276-25748/src/sprite/ghostright.png").getImage();
    }

    /**
    * Draws sprite/animation based on direction given
    */
    public void draw(Graphics2D g2d, int dirX, int dirY, int xPos, int yPos)
    {
        if (dirX == -1) {
            image = leftImage;
        } else if (dirX == 1) {
            image = rightImage;
        } else if (dirY == -1) {
            image = upImage;
        } else {
            image = downImage;
        }
        Helper.drawImage(image, g2d, xPos, yPos);
    }
}
