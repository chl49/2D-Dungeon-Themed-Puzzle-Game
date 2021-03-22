package Game;

public class Movable {
    protected int pos;
    protected int nextPos;
    protected boolean hasUpdated = false;

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
