public class Board {

    int rowCount;
    int columnCount;
    Cell [] cellArray;


    public Board(int numberOfRows, int numberOfColumns, Cell [] cells) {
        rowCount = numberOfRows;
        columnCount = numberOfColumns;
        cellArray = cells;
    }

    public void drawBoard() {
        String fileName = "";
    }

    public Cell getCell(int rowOfCell, int columnOfCell, String cellType) {
        Cell cell = cellArray[1];
        return cell;
    }
}
