package Game;

public class Cell {

    int cellPos; // position
    char cellChar;

    // default constructor
    public Cell() {}

    // constructor which store the character letter
    public Cell(int pos, char cellContent) {
        this.pos = pos;
        cellChar = cellContent;
    }

    // returns the content of the cell
    public char getCellChar() {
        return cellChar;
    }

    public int getPosition()
    {
        return pos;
    }

    public boolean isBlocking()
    {
        return isBlocking;
    }

}