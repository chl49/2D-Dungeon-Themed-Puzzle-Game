/*
Maggie Ruan
rewardPunishment class
Display and calculate reward and punishment
Requires: current player position
 */
package Game;
abstract public class Interactable extends Entity{
    //call game manager to check valid move
    int i = get Row();
    int j = get Col();
    int result = CheckCollisions (i, j);

}
