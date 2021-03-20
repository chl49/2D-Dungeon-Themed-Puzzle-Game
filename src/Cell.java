import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cell extends Board {

    int cellRow;
    int cellColumn;
    char typeOfCell;
    short sidesWithWall;

    public Cell() {

    }

    public Cell(char cellType) {
        setType(cellType);
    }

    public Cell(int rowOfCell, int columnOfCell, String cellType) {
        cellRow = rowOfCell;
        cellColumn = columnOfCell;
        setType(cellType);
    }

    public void setType(char cellType) {
        typeOfCell = cellType;
    }

    public Cell getCell(int rowOfCell, int columnOfCell, String cellType) {
        Cell cell = cellArray[1];
        return cell;
    }

    public String getType() {
        return this.typeOfCell;
    }

    public String getGrid() throws IOException {
        String result;
        FileReader fr = new FileReader("src/input.txt");
        BufferedReader reader = new BufferedReader(fr);

        String line;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }
        result = content.toString();

        reader.close();
        fr.close();

        return result;
    }


}