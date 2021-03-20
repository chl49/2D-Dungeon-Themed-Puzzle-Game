import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cell {

    int cellRow; // position
    char cellChar;

    public Cell(char cellContent) {
        cellChar = cellContent;
    }

    public char getCellChar() {
        return cellChar;
    }

}