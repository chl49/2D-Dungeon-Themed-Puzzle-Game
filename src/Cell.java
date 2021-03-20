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

    public String getType() {
        return this.typeOfCell;
    }

}