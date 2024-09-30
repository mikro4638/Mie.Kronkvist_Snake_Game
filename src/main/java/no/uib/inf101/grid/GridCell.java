package no.uib.inf101.grid;

/**
 * A single cell in a grid, containing a position and a value.
 *
 * @param <E> the type of the value stored in the cell
 */
public record GridCell<E>(CellPosition pos, E value) {
    
    /**
     * Constructs a new cell with the given position and value.
     * 
     * <p>
     * The record type automatically generates a constructor based on the fields.
     * For example:
     * {@code GridCell<CellPosition> cell = new GridCell<>(new CellPosition(0, 0), value);}
     * </p>
     * 
     * <p>
     * The record type allows the use of a compact constructor.
     * </p>
     */
}
