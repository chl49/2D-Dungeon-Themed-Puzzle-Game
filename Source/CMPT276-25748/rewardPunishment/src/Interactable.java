/*
Maggie Ruan
rewardPunishment class
Display and calculate reward and punishment
Requires: current player position
 */
package Game;
abstract public class Interactable extends Entity{
    //call game manager to check valid move
    protected int position = 0;
    protected int score = 0;

    public int getPosition()
    {
        return position;
    }

    public int getScore()
    {
        return score;
    }


}
