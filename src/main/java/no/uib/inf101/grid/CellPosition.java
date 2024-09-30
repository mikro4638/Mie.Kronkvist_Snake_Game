package no.uib.inf101.grid;

/**
 * A simple data class representing the position of a cell in a two-dimensional grid.
 * This class is implemented as a Java record with two components: row and col.
 */
public record CellPosition(int row, int col) {

    /**
     * Retrieves the row number of the cell.
     * 
     * @return the row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column number of the cell.
     * 
     * @return the column number
     */
    public int getCol() {
        return col;
    }
}
