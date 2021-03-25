package Game;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
* PlayerAnimation class
* Rendering component of the Player class
*/
public class PlayerAnimation {
    
    private final int AnimationDelay = 10;
    private int animPos = 0; //Animation Looper

    ArrayList<Image> upImages;
    ArrayList<Image> downImages;
    ArrayList<Image> leftImages;
    ArrayList<Image> rightImages;

    ArrayList<Image> currentAnim = null;
    ArrayList<Image> lastAnim = null;

    private int currentAnimTimer = 0;

    public void loadImages() {
        upImages = new ArrayList<Image>();
        upImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup1.png").getImage());
        upImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup2.png").getImage());
        upImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup3.png").getImage());
        upImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyup4.png").getImage());

        downImages = new ArrayList<Image>();
        downImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown1.png").getImage());
        downImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown2.png").getImage());
        downImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown3.png").getImage());
        downImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnydown4.png").getImage());

        leftImages = new ArrayList<Image>();
        leftImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft1.png").getImage());
        leftImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft2.png").getImage());
        leftImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft3.png").getImage());
        leftImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyleft4.png").getImage());

        rightImages = new ArrayList<Image>();
        rightImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright1.png").getImage());
        rightImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright2.png").getImage());
        rightImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright3.png").getImage());
        rightImages.add(new ImageIcon("Source/CMPT276-25748/src/sprite/sunnyright4.png").getImage());
    }

    /**
    * Draws sprite/animation based on direction given
    */
    public void draw(Graphics2D g2d, int dirX, int dirY, int xPos, int yPos) {

        lastAnim = currentAnim;

        if (dirX == -1) {
            currentAnim = leftImages;
        } else if (dirX == 1) {
            currentAnim = rightImages;
        } else if (dirY == -1) {
            currentAnim = upImages;
        } else {
            currentAnim = downImages;
        }

        //update animation if the same direction, else, reset
        if(lastAnim != null && currentAnim == lastAnim)
        {
            updateAnimCounter();
        }
        else
        {
            resetAnimCounter();
        }

        Helper.drawImage(currentAnim.get(animPos), g2d, xPos, yPos);
    }

    /**
    * Applies frame delay so sprites cycle more visibly
    */
    private void updateAnimCounter()
    {
        currentAnimTimer--;

        if(currentAnimTimer <= 0)
        {
            animPos = (animPos + 1 ) % currentAnim.size();
            currentAnimTimer = AnimationDelay;
        }
    }

    private void resetAnimCounter()
    {
        animPos = 0;
    }
}
