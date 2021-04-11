/**
* Cell class
* 
* Used for creating instances of cells with various fields to use in several places in the program
* 
*/

package Game;

public class Cell {

    protected int pos; // position
    protected char cellChar;
    protected boolean isBlocking = false;

    /*
    * constructor which store the character letter
    */
    public Cell(int pos, char cellContent) {
        this.pos = pos;
        cellChar = cellContent;
    }

    /*
    * returns the content of the cell
    */
    public char getCellChar() {
        return cellChar;
    }

    public void setCellChar(char cellContent) {
        cellChar=cellContent;
    }

    /*
    * returns the position of the cell
    */
    public int getPosition()
    {
        return pos;
    }

    /*
    * checks if the cell is a barrier or not
    */
    public boolean isBlocking()
    {
        return isBlocking;
    }

}