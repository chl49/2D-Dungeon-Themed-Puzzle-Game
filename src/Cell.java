import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cell {

    int cellRow; // position
    char typeOfCell; // n
    short sidesWithWall;

    public Cell() {

    }

    public Cell(char cellType) {
        setType(cellType);
    }

    public Cell(int rowOfCell, String cellType) {
        cellRow = rowOfCell;
        setType(cellType);
    }

    public void setType(char cellType) {
        typeOfCell = cellType;
    }

    public Cell getCell(int rowOfCell, int columnOfCell, String cellType) {
        Cell cell = cellArray[1];
        return cell;
    }

    public char getType() {
        return this.typeOfCell;
    }


}