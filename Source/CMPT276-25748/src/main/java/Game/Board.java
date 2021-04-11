/**
* Board class
* Has all the data about the entire board, the types of cells and creates an array of cells.
* Has various methods which return information about the board and the cells and also to draw the board.
*/

package Game;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Board implements Renderable {

    private Image map = new ImageIcon("Source/CMPT276-25748/src/sprite/map.png").getImage();
    private Image newmap = new ImageIcon("Source/CMPT276-25748/src/sprite/newmap.png").getImage();
    private Image image = map;
    String inputFile;
    int rowCount;
    int rowSize;
    Cell [] cellArray;
    //private String fileContent;

    // constructor takes the name of an existing text file,
    // reads from the file,
    // and creates an array of Cells

    /**
    * Reads the contents of a file which allows us to render the different cells on the game board.
    * Creates an array of type cell.
    *
    * @param    fileName gets the file name to load various characters from.
    */
    public Board(String fileName)  throws IOException {

        // reading from the text file and storing into a String variable
        var fileContent = readFile(fileName);
        inputFile=fileContent;
        // pass fileContent to a function that will create a cell array
        cellArray = createCellArray(fileContent);
    }

    /**
    * Method to read from the text file and store into a string called result
    *
    * @param    fileName gets the file name to load various characters from.
    */
    public String readFile(String fileName) throws IOException {

        String result;
        FileReader fr = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fr);
        String line;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            content.append(line);
            rowCount++;

            var length = line.length();

            if(rowSize < length)
            {
                rowSize = length;
            }
        }

        result = content.toString();

        reader.close();
        fr.close();

        return result;
    }

    /**
    * returns the number of columns of cells on the game board
    */  
    public int getNoOfColumns () {
        return rowSize;
    }

    /*
    * returns the number of rows of cells on the game board
    */
    public int getNoOfRows () {
        return rowCount;
    }

    /*
    * method creates an array of cells with different letters in them for now showing the content of the cell.
    */
    private Cell[] createCellArray(String fileContent) {

        var length = fileContent.length();

        Cell[] newCellArray = new Cell[length];
        for (int i = 0; i < fileContent.length(); i++) {

            var type = fileContent.charAt(i);

            if(type == 'W')
            {
                newCellArray[i] = new Wall(i, type);
            }
            else
            {
                newCellArray[i] = new Cell(i, type);
            }
            
        }
        return newCellArray;
    }

    public void resetCellArray() {
        // pass fileContent to a function that will create a cell array
        cellArray = createCellArray(inputFile);
    }

    /*
    * returns the array of cells
    */
    public Cell[] getCellArray() {
        return cellArray;
    }

    public void setCellArray(Cell[] newArray) {
        cellArray=newArray;
    }


    public int calcXPos(int position)
    {
        return position % rowSize;
    }

    public int calcYPos(int position)
    {
        return position / rowSize;
    }

    public int calcPosFrom2D(int x, int y)
    {
        return y * rowSize + x;
    }

    /**
    * checks if the cell is an empty cell through which the player and enemy can pass.
    * 
    * @param    position - gives the position of the cell.
    */
    public boolean isEmpty(int position)
    {
        if(position >= 0 && position < cellArray.length)
        {
            if(!cellArray[position].isBlocking)
            {
                return true;
            }
        }

        return false;
    }

    public void updateMap(){
        image=newmap;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Helper.drawImage(image, g2d, 0, 0);
    }

    @Override
    public boolean isVisible() {
        return true;
    }

}
