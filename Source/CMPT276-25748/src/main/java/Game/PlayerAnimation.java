package Game;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.util.ArrayList;

public class PlayerAnimation {
    
    private final int AnimationDelay = 5;
    private final int AnimationFrames = 4;
    private int AnimCooldown = AnimationDelay;
    private int AnimPos = 0; //Animation Looper

    ArrayList<Image> upImages;
    ArrayList<Image> downImages;
    ArrayList<Image> leftImages;
    ArrayList<Image> rightImages;

    public void loadImages() {
        //SPRITES 
        //ADD OTHER CHARACTER SPRITES HERE
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

    public void doAnim() {

        AnimCooldown--;

        if (AnimCooldown <= 0) {
            AnimCooldown = AnimationDelay;
            AnimPos++;

            if (AnimPos == AnimationFrames) {
                AnimPos = 0;
            }
        }
    }

    public void draw(Graphics2D g2d, int dirX, int dirY, int xPos, int yPos) {

        if (dirX == -1) {
            Helper.drawImage(leftImages.get(AnimPos), g2d, xPos, yPos);
        } else if (dirX == 1) {
            Helper.drawImage(rightImages.get(AnimPos), g2d, xPos, yPos);
        } else if (dirY == -1) {
            Helper.drawImage(upImages.get(AnimPos), g2d, xPos, yPos);
        } else {
            Helper.drawImage(downImages.get(AnimPos), g2d, xPos, yPos);
        }
    }
}