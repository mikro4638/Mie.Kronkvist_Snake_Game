package no.uib.inf101.snake.model.food;

import no.uib.inf101.grid.CellPosition;

import java.util.Random;

/**
 * A factory that creates food objects at random locations.
 */
public class RandomeFoodFactory implements FoodFactory {

    private final int rows; 
    private final int cols; 


    /**
     * Creates a new RandomFoodFactory.
     *
     * @param rows number of rows in the grid
     * @param cols number of columns in the grid
     */
    public RandomeFoodFactory(int rows, int cols) {
        this.rows = rows; 
        this.cols = cols; 
    }


    @Override
    public Food createFood() {
        Random random = new Random(); 
        int row = random.nextInt(rows); 
        int col = random.nextInt(cols); 
        return new Food(new CellPosition(row, col)); 
    }
}

