/*
Maggie Ruan
rewardPunishment class
Display and calculate reward and punishment
Requires: current player position
 */
package Game;
 public class Interactable /*extends Entity*/{
    //call game manager to check valid move
    protected int position = 0;
    protected int score = 0;

    public int getPosition()
    {
        System.out.println("1");
        return position;
    }

    public void setPosition(int position)
    {
        System.out.println("1");
        this.position = position;
    }

    public int getScore()
    {
        return score;
    }

   


}
