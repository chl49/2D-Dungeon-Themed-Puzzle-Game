package Game;
import java.awt.Graphics2D;


public class Rewards extends Interactable implements Renderable{

    public Rewards (int inputPos, int inputScore){
        position = inputPos;
        score = inputScore;
    }

    RewardImage render;

    public Rewards()
    {
        render = new RewardImage();
        render.loadImages();
    }

    @Override
    public void draw(Graphics2D g2d) {
        //System.out.println("1");

        var pos2Di = Helper.get2Dpos(position);
        render.draw(g2d, pos2Di[6], pos2Di[2]);
    }


    @Override
    public boolean isVisible() {
        return true;
    }


    
}
