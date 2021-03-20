import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Board {

    int rowCount;
    int rowSize;
    Cell [] cellArray;
    String fileContent;

    public Board() {

    }

    public Board(String fileName)  throws IOException {
        fileContent = readFile(fileName);
        // pass fileContent to a function that will create a cell array
        cellArray = createCellArray();
    }

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

    public int getNoOfColumns () {
        return rowSize;
    }

    public int getNoOfRows () {
        return rowCount;
    }

    public Cell[] createCellArray() {

        Cell[] newCellArray = new Cell[100];
        for (int i = 0; i < fileContent.length(); i++) {
            newCellArray[i] = new Cell(fileContent.charAt(i));
        }
        return newCellArray;
    }

    public String getFileContent() {

//        Cell[] oneCell = null;
        return fileContent;
    }

    public Cell[] getCellArray() {
        return cellArray;
    }

    public void printAll(int[][] a) {
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a.length; ++j) {
                System.out.print(a[i][j] + " ");
            }
        System.out.println();
        }
    }
}
