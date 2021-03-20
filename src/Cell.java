import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cell extends Board {

    int cellRow;
    int cellColumn;
    String typeOfCell;
    short sidesWithWall;

    public Cell() {

    }

    public Cell(int rowOfCell, int columnOfCell, String cellType) {
        cellRow = rowOfCell;
        cellColumn = columnOfCell;
        setType(cellType);
    }

    public void setType(String cellType) {
        typeOfCell = cellType;
    }

    public Cell getCell(int rowOfCell, int columnOfCell, String cellType) {
        Cell cell = cellArray[1];
        return cell;
    }

    public String getType() {
        return this.typeOfCell;
    }

    public void displayGrid() throws IOException {
        String result;
        FileReader fr = new FileReader("src/input.txt");
        BufferedReader reader = new BufferedReader(fr);

        String line;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
        result = content.toString();
        System.out.println(result);
        reader.close();
        fr.close();
    }


}