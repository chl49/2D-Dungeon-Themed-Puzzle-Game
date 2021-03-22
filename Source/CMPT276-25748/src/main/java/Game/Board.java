import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Board {

    int rowCount;
    int rowSize;
    Cell [] cellArray;
    String fileContent;

    // empty constructor
    public Board() {

    }

    // constructor takes the name of an existing text file,
    // reads from the file,
    // and creates an array of Cells
    public Board(String fileName)  throws IOException {

        // reading from the text file and storing into a String variable
        fileContent = readFile(fileName);

        // pass fileContent to a function that will create a cell array
        cellArray = createCellArray();
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
            rowSize = line.length();
            rowCount++;
        }

        result = content.toString();

        reader.close();
        fr.close();

        return result;

    }

    // returns the number of columns of cells on the game board
    public int getNoOfColumns () {
        return rowSize;
    }

    // returns the number of rows of cells on the game board
    public int getNoOfRows () {
        return rowCount;
    }

    // method creates an array of cells with different letters in them for now showing the content of the cell.
    public Cell[] createCellArray() {

        Cell[] newCellArray = new Cell[100];
        for (int i = 0; i < fileContent.length(); i++) {
            newCellArray[i] = new Cell(fileContent.charAt(i));
        }
        return newCellArray;
    }

    // returns the contents of the text file
    public String getFileContent() {

//        Cell[] oneCell = null;
        return fileContent;
    }

    // returns the array of cells
    public Cell[] getCellArray() {
        return cellArray;
    }

}
