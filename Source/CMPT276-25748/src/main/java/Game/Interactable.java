package Game;

public class Interactable {

   protected int position;
   protected int score = 0;
   protected boolean isActive = false;
 
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

   public boolean isActive()
   {
       return isActive;
   }

   public void setActive(boolean isActive)
   {
       this.isActive = isActive;
   }
 
}
