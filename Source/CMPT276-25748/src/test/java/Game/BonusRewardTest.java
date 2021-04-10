package Game;
import java.awt.Graphics2D;

/**
* BonusReward class
* The class will have a duration time that whenever it passes the time, 
* the bonus reward will disappear. 
*/
public class BonusRewardTest extends Interactable implements Renderable{

    BonusRewardImage render;
    private int duration = 20;
    private boolean isExpired= false;
    
    public void decreaseLife() {
        duration--;
    }

    /** 
     * The function will check if the reward is expired or not
     * @return boolean
     */
    public boolean isExpired() {
        if (duration<=0){
            return true;
        }
        return false;
    }
    /** 
     * The function will check if the reward is expired or not
     * @param inputPos      takes the position input
     * @param inputScore    takes the score input
     * @return boolean
     */
    public BonusRewardTest(int inputPos, int inputScore)
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
    /** 
     * The function will find positions and draw the sprite
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) 
    {
        var pos2D = HelperTest.get2Dpos(position);
        render.draw(g2d, pos2D[0], pos2D[1]);
    }
  
    /** 
     * @param isVisible()
     * @return boolean
     */
    @Override
    public boolean isVisible() 
    {
        return isActive;
    }
    
}
