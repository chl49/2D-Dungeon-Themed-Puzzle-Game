public class Cell {

    int cellRow;
    int cellColumn;
    String typeOfCell;
    short sidesWithWall;

    public Cell(int rowOfCell, int columnOfCell, String cellType, short noOfSidesCoveredWithWall) {
        cellRow = rowOfCell;
        cellColumn = columnOfCell;
        setType(cellType, noOfSidesCoveredWithWall);
    }

    public void setType(String cellType, short noOfSidesCoveredWithWall) {
        typeOfCell = cellType;
        sidesWithWall = noOfSidesCoveredWithWall;
    }

    public String getType() {
        return this.typeOfCell;
    }

}