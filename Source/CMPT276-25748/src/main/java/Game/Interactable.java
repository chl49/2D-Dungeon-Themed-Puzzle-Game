package Game;

/**
 * Interactable class
 * This class takes the position and score input and set the inputs.
 * And check if the reward or punishment have been achieved or not
 */
public class Interactable {

   protected int position;
   protected int originalPos;
   protected int score = 0;
   protected boolean isActive = false;
 
   
   /** 
    * @return int
    */
   public int getPosition()
   {
       return position;
   }

   public int getOriginalPos()
   {
       return originalPos;
   }
 
   
   /** 
    * @param position
    */
   public void setPosition(int position)
   {
       this.position = position;
   }
 
   
   /** 
    * @return int
    */
   public int getScore()
   {
       return score;
   }

   
   /** 
    * @return boolean
    */
   public boolean isActive()
   {
       return isActive;
   }

   
   /** 
    * @param isActive
    */
   public void setActive(boolean isActive)
   {
       this.isActive = isActive;
   }
 
}
