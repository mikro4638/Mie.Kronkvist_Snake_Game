package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A generic class representing a two-dimensional grid with elements of type E.
 * The grid stores elements in a two-dimensional list.
 *
 * @param <E> The type of elements in the grid.
 */
public class Grid<E> implements IGrid<E> {

    private int rows;
    private int cols;
    private List<List<E>> cells;

    /**
     * Constructs a grid with the specified number of rows and columns.
     * All cells are initialized to {@code null}.
     *
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    /**
     * Constructs a grid with the specified number of rows, columns, and a default value for all cells.
     *
     * @param rows        the number of rows in the grid
     * @param cols        the number of columns in the grid
     * @param defaultValue the value to initialize all cells with
     */
    public Grid(int rows, int cols, E defaultValue) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new ArrayList<>();
        // Initialize each row with the default value
        for (int i = 0; i < rows; i++) {
            List<E> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(defaultValue); // Set default value for each cell
            }
            cells.add(row);
        }
    }

    @Override
    public int rows() {
        return rows; // Return the number of rows
    }

    @Override
    public int cols() {
        return cols; // Return the number of columns
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> list = new ArrayList<>();
        // Populate the list with GridCell objects for each position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CellPosition pos = new CellPosition(i, j);
                E value = get(pos); // Get the value at the current position
                GridCell<E> cell = new GridCell<>(pos, value);
                list.add(cell);
            }
        }
        return list.iterator(); // Return an iterator for the list of cells
    }

    @Override
    public void set(CellPosition pos, E value) {
        // Check if the position is valid before setting the value
        if (!positionIsOnGrid(pos))
            throw new IndexOutOfBoundsException("Given position is not within grid: (" + pos.row() + ", " + pos.col() + ")");
        cells.get(pos.row()).set(pos.col(), value); // Set the value at the specified position
    }

    @Override
    public E get(CellPosition pos) {
        // Check if the position is valid before getting the value
        if (!positionIsOnGrid(pos))
            throw new IndexOutOfBoundsException("Given position is not within grid: (" + pos.row() + ", " + pos.col() + ")");
        return cells.get(pos.row()).get(pos.col()); // Return the value at the specified position
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        // Check if the given position is within the bounds of the grid
        boolean isWithinRowBound = pos.row() >= 0 && pos.row() < rows;
        boolean isWithinColBound = pos.col() >= 0 && pos.col() < cols;
        return isWithinRowBound && isWithinColBound; // Return true if the position is valid
    }
}