package Game;

/**
* Movable class
* Contains position information and desired position information.
* TestManager will update the position in the Test loop
*/
public class Movable {
    protected int pos;
    protected int nextPos;

    public int getPosition()
    {
        return pos;
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
