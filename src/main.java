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

        Board newBoard = new Board("src/input.txt");

        Cell[] newCellArray = newBoard.getCellArray();

        noOfRows = newBoard.getNoOfRows();
        noOfColumns = newBoard.getNoOfColumns();

        for (int i = 0; i < (noOfRows*noOfColumns); i++) {
            contentsOfArray += newCellArray[i].getCellChar();
        }

        System.out.println("Contents from the text file: " + newBoard.getFileContent()
                + "\nNumber of Rows = " + Integer.toString(noOfRows)
                + "\nNumber of Columns = " + Integer.toString(noOfColumns)
                + "\nContents from the Cell Array: " + contentsOfArray
                + "\nTotal number of cells: " + (noOfRows*noOfColumns));

    }
}