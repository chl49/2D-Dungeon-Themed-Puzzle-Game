/**
* Wall class
* 
* Creates instances of wall types of cells. Can be used in the program to see if a cell is a barrier or not
* 
*/

package Game;


public class WallTest extends Cell{

    /**
    * Wall constructor sets the condition true which makes the cell a barrier.
    * 
    * @param  pos - the position of the cell
    * @param  cellContent - the character letter in the cell
    */
    public WallTest(int pos, char cellContent)
    {
        super(pos, cellContent);

        isBlocking = true;
    }


}
