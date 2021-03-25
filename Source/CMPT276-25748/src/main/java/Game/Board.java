package Game;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Board implements Renderable {

    private Image image = new ImageIcon("Source/CMPT276-25748/src/sprite/map.png").getImage();
    int rowCount;
    int rowSize;
<<<<<<< HEAD
    Cell [] cellArray;
    //private String fileContent;
=======
    Cell[] cellArray;
    String fileContent;

    // empty constructor
    // public Board() {

    // }
>>>>>>> f371a99 (got the updated master branch into my board2 branch)

    // constructor takes the name of an existing text file,
    // reads from the file,
    // and creates an array of Cells
    public Board(String fileName) throws IOException {

        // reading from the text file and storing into a String variable
        var fileContent = readFile(fileName);

        // pass fileContent to a function that will create a cell array
        cellArray = createCellArray(fileContent);
    }

    // method to read from the text file
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

    // returns the number of columns of cells on the game board
    public int getNoOfColumns() {
        return rowSize;
    }

    // returns the number of rows of cells on the game board
    public int getNoOfRows() {
        return rowCount;
    }

    // method creates an array of cells with different letters in them for now
    // showing the content of the cell.
    public Cell[] createCellArray() {

        var length = fileContent.length();

        Cell[] newCellArray = new Cell[length];
        for (int i = 0; i < fileContent.length(); i++) {

            var type = fileContent.charAt(i);

        return fileContent;
    }

    // returns the array of cells
    public Cell[] getCellArray() {
        return cellArray;
    }

    public int calcXPos(int position) {
        return position % rowSize;
    }

    public int calcYPos(int position) {
        return position / rowSize;
    }

    public int calcPosFrom2D(int x, int y) {
        return y * rowSize + x;
    }

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

    // @Override
    // public void draw(Graphics2D g2d) {
    // // TODO Auto-generated method stub

    // }
    @Override
    public void draw(Graphics2D g2d) {
        Helper.drawImage(image, g2d, 0, 0);
    }

    // @Override
    // public boolean isVisible() {
    // // TODO Auto-generated method stub
    // return false;
    // }
    @Override
    public boolean isVisible() {
        return true;
    }

    public void identifyCells() {
        for (int i = 0; i < cellArray.length; i++) {
            switch (cellArray[i].getCellChar()) {
            case 'w':
                System.out.println("wall-");
                break;
            case ' ':
                System.out.println("empty cell-");
                break;
            case 'e':
                System.out.println("enemy-");
                break;
            case 'r':
                System.out.println("reward-");
                break;
            case 'b':
                System.out.println("bonus reward-");
                break;
            case 'p':
                System.out.println("penalty-");
                break;
            case 'm':
                System.out.println("main character-");
                break;
            case 's':
                System.out.println("start-");
                break;
            case 'f':
                System.out.println("finish");
                break;
            }
        }
    }

}
