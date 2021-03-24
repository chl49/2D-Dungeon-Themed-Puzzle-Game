package Game;


public class Interactable /*extends Entity*/{
   //call game manager to check valid move
   protected int position;
   protected int score = 0;
 
   public int getPosition()
   {
       return position;
   }
 
   public void setPosition(int position)
   {
       this.position = position;
   }
 
   public int getScore()
   {
       return score;
   }
 
}
