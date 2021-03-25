package Game;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import java.util.TimerTask;

public class BonusReward extends Interactable implements Renderable{

    BonusRewardImage render;
    protected int duration = 20;
    boolean isExpired= false;
    
    public void DecreaseLife() {
        duration--;
    }

    public boolean isExpired() {
        if (duration<=0){
            return true;
        }
        return isExpired;
    }
 
    public BonusReward(int inputPos, int inputScore)
    {
        
         position = inputPos;
         score = inputScore;
         isActive = true;

        if (isExpired){
            isActive = false;

        }else{
            render = new BonusRewardImage();
            render.loadImages();
        }
        
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
