package no.uib.inf101.snake.model;

import no.uib.inf101.grid.Grid;

/**
 * Represents the game board for the snake.
 * SnakeBoard is a grid-based structure that manages the game state.
 */
public class SnakeBoard extends Grid<Integer> {
    
    /**
     * Creates a new SnakeBoard with the specified number of rows and columns.
     *
     * @param rows the number of rows in the board
     * @param cols the number of columns in the board
     */
    public SnakeBoard(int rows, int cols) {
        super(rows, cols, 0); // Uses 0 as a default value
    }
    
}

