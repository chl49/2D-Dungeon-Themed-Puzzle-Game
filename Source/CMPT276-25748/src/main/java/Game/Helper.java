package Game;

public class Helper {
    
    public static int rowPos(int position)
    {
        return GameManager.instance().getBoard().calcRowPos(position);
    }

    public static int colPos(int position)
    {
        return GameManager.instance().getBoard().calcColPos(position);
    }
}
