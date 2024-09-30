package no.uib.inf101.snake.model.food;

/**
 * This interface allows us to create food for the snake.
 * FoodFactory helps us in creating new food objects.
 */
public interface FoodFactory {
    
    /**
     * Creates a new food item.
     * 
     * @return a new food item for the snake
     */
    Food createFood();
}

