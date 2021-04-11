package Game;

/**
* Movable class
* Contains position information and desired position information.
* GameManager will update the position in the game loop
*/
public class Movable {
    protected int pos;
    protected int nextPos;
    protected int originalPos;

    public int getPosition()
    {
        return pos;
    }

    public int getOriginalPos()
   {
       return originalPos;
   }

    public int getNextPosition()
    {
        return nextPos;
    }

    public void setPosition(int pos)
    {
        this.pos = pos;
    }

    public void setNextPosition(int nextPos)
    {
        this.nextPos = nextPos;
    }

    public void updatePosition()
    {
        pos = nextPos;
    }
}
