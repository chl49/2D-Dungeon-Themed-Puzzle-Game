package Game;
import java.awt.Graphics2D;
public class BonusReward extends Interactable implements Renderable{

    BonusRewardImage render;
    private int duration = 20;
    private boolean isExpired= false;
    
    public void decreaseLife() {
        duration--;
    }

    public boolean isExpired() {
        if (duration<=0){
            return true;
        }
        return false;
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
