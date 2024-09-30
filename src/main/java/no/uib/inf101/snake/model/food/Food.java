package no.uib.inf101.snake.model.food;

import no.uib.inf101.grid.CellPosition;

/**
 * Represents the food object that the snake can eat.
 * The Food object contains a position in the grid.
 */
public class Food {
    private CellPosition position; // The position of the food object

    /** 
     * Constructor to create a new Food object with a given position.
     * @param position the position of the food object
     */
    public Food(CellPosition position) {
        this.position = position;
    }

    /** 
     * Retrieves the position of the food object. 
     * @return the position of the food object
     */
    public CellPosition getPosition() {
        return position;
    }

    /** 
     * Sets the position of the food object to a new value. 
     * @param position the new position of the food object
     */
    public void setPosition(CellPosition position) {
        this.position = position;
    }
}
