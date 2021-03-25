package Game;

public class Wall extends Cell{

    public Wall(int pos, char cellContent)
    {
        super(pos, cellContent);

        isBlocking = true;
    }


}
