package Game;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BoardTest {

    /**
     * Test to see if the cell array is implemented successfully by comparing
     * the length of the array.
     */
    @Test
    public void cellArrayCreationTest() {
        char[] content = {'W','o','m','o','o','o','o','o','o','b','o','o','o','o','W'};
        
        var length = content.length;

        Cell[] newCellArray = new Cell[length];
        
        for (int i = 0; i < length; i++) {

            var type = content[i];

            if(type == 'W')
            {
                newCellArray[i] = new Wall(i, type);
            }
            else
            {
                newCellArray[i] = new Cell(i, type);
            }
        }
        
        assertEquals(length, newCellArray.length);
    }

    /**
     * Test to see if the file is being read successfully by comparing 
     * the number of cells on board.
     */
    @Test
    public void readFromFileTest() throws IOException {
        String file = "src/resources/input.txt";
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line;
        int rowCount=0;
        int rowSize=0;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            content.append(line);
            rowCount++;
            var length = line.length();
            if(rowSize < length)
            {
                rowSize = length;
            }
        }

        reader.close();
        fr.close();

        System.out.println(content
        + "\n\nTotal number of rows on the board = " + rowSize
        + "\nTotal number of columns on the board = " + rowCount
        + "\nTotal number of cells on the board = " + rowSize*rowCount);

        assertTrue(rowSize*rowCount == 225);
        
    }

}
