import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IllegalStateException, IOException {

        String gridString;

        Cell oneCell = new Cell();
        gridString = oneCell.getGrid();
        System.out.println(gridString + " " + "...");
        Cell[][] cellArray = null;

        int rowCounter = 0;
        for (int i = 0; i < gridString.length(); i++) {
            Cell newCell = new Cell(gridString.charAt(i));
        }
    }
}