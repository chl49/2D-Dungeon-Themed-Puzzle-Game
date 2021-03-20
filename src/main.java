import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IllegalStateException, IOException {

        String gridString = "";
        int noOfRows = 0;
        int noOfColumns = 0;
        String contentsOfArray = "";

        // creating a new board from a text file
        Board newBoard = new Board("src/input.txt");

        // storing the array of cells in newCellArray
        Cell[] newCellArray = newBoard.getCellArray();

        // getting the number of rows on the board
        noOfRows = newBoard.getNoOfRows();

        // getting the number of columns on the board
        noOfColumns = newBoard.getNoOfColumns();

        // going through the array and storing the contents together as a string
        for (int i = 0; i < (noOfRows*noOfColumns); i++) {
            contentsOfArray += newCellArray[i].getCellChar();
        }

        // printing some stuff just to see what was created
        System.out.println("Contents from the text file: " + newBoard.getFileContent()
                + "\nNumber of Rows = " + Integer.toString(noOfRows)
                + "\nNumber of Columns = " + Integer.toString(noOfColumns)
                + "\nContents from the Cell Array: " + contentsOfArray
                + "\nTotal number of cells: " + (noOfRows*noOfColumns));

    }
}