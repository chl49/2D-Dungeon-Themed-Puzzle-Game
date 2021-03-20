import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Board {

    int rowCount;
    int columnCount;
    Cell [] cellArray;
    String fileContent;

    public Board() {

    }

    public Board(String fileName)  throws IOException {
        fileContent = readFile(fileName);
        // pass it to a function that will create a cell array
    }

    public String readFile(String fileName) throws IOException {
        String result;
        FileReader fr = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fr);

        String line;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            content.append(line);
            rowsize = line.length();
            content.append("\n");
            rowCount++;
        }
        result = content.toString();

        reader.close();
        fr.close();

        return result;
    }

    public static int getRowSize () {
        return rowSize;
    }

    public void drawBoard() {
        String fileName = "";
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
