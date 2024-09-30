package no.uib.inf101.snake.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.snake.controller.ControllableSnakeModel;
import no.uib.inf101.snake.view.ViewableSnakeModel;
import no.uib.inf101.snake.model.food.Food;
import no.uib.inf101.snake.model.food.FoodFactory;
import no.uib.inf101.snake.model.food.RandomeFoodFactory;
import no.uib.inf101.snake.model.snake.SnakePiece;
import java.util.ArrayList;
import java.util.List;

/**
 * SnakeModel represents the logic of the Snake game.
 * It contains the game's data, including the snake, food, and game board.
 */
public class SnakeModel implements ViewableSnakeModel, ControllableSnakeModel {

    private SnakeBoard board;
    private Food food;
    private List<SnakePiece> snake;
    private GameState gameState;
    private int score = 0;

    /**
     * Constructor for SnakeModel.
     *
     * @param board the game board
     */
    public SnakeModel(SnakeBoard board) {
        this.board = board;
        this.snake = createInitialSnake();
        this.food = createFood();
        this.gameState = GameState.ACTIVE_GAME;
    }

    
    @Override
    public GridDimension getDimension() {
        return board;
    }

    
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's score.
     *
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    
    @Override
    public Iterable<GridCell<Integer>> getTilesOnBoard() {
        return board;
    }

    
    @Override
    public boolean moveSnake(int deltaRow, int deltaCol) {
        if (deltaRow == 0 && deltaCol == 0) {
            return true;
        }

        CellPosition headPos = snake.get(0).getPosition();
        CellPosition newHeadPos = new CellPosition(headPos.getRow() + deltaRow, headPos.getCol() + deltaCol);

        if (isLegalMove(newHeadPos, board)) {
            SnakePiece newHead = new SnakePiece(newHeadPos);
            snake.add(0, newHead);
            checkForFoodCollision(newHead);

            if (snakeCollidesWithItself()) {
                gameState = GameState.GAME_OVER;
                return false;
            }

            snake.remove(snake.size() - 1);
            return true;
        } else {
            gameState = GameState.GAME_OVER;
            return false;
        }
    }

    
    @Override
    public List<SnakePiece> getSnake() {
        return snake;
    }

    
    @Override
    public Food getFood() {
        return food;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Creates the initial snake at the start of the game.
     *
     * @return a list of snake pieces representing the initial snake
     */
    public List<SnakePiece> createInitialSnake() {
        List<SnakePiece> initialSnake = new ArrayList<>();
        for (int col = 3; col >= 1; col--) {
            initialSnake.add(new SnakePiece(new CellPosition(5, col)));
        }
        return initialSnake;
    }

    /**
     * Creates a new food object at a random position on the board.
     *
     * @return a new food object
     */
    private Food createFood() {
        FoodFactory foodFactory = new RandomeFoodFactory(board.rows(), board.cols());
        Food newFood;

        do {
            newFood = foodFactory.createFood();
        } while (snakeOccupiesPosition(newFood.getPosition()));

        return newFood;
    }

    /**
     * Checks if the snake occupies a specific position.
     *
     * @param pos the position to check
     * @return true if the snake occupies the position, otherwise false
     */
    public boolean snakeOccupiesPosition(CellPosition pos) {
        for (SnakePiece piece : snake) {
            if (piece.getPosition().equals(pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the snake collides with itself.
     *
     * @return true if the snake collides with itself, otherwise false
     */
    private boolean snakeCollidesWithItself() {
        CellPosition headPos = snake.get(0).getPosition();
        for (int i = 1; i < snake.size(); i++) {
            if (headPos.equals(snake.get(i).getPosition())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given position is within the game board.
     *
     * @param pos the position to check
     * @param board the game board to check against
     * @return true if the position is within the board, otherwise false
     */
    private boolean isLegalMove(CellPosition pos, SnakeBoard board) {
        return pos.getRow() >= 0 && pos.getRow() < board.rows() &&
               pos.getCol() >= 0 && pos.getCol() < board.cols();
    }

    /**
     * Checks if the snake collides with the food.
     *
     * @param head the snake piece to check against the food
     */
    private void checkForFoodCollision(SnakePiece head) {
        if (head.getPosition().equals(food.getPosition())) {
            updateFoodPosition();
            increaseSnakeLength();
            score++;
        }
    }

    /**
     * Updates the position of the food.
     */
    private void updateFoodPosition() {
        FoodFactory foodFactory = new RandomeFoodFactory(board.rows(), board.cols());
        Food newFood;
        do {
            newFood = foodFactory.createFood();
        } while (snakeOccupiesPosition(newFood.getPosition()));
        food = newFood;
    }

    /**
     * Increases the length of the snake by adding an extra piece.
     */
    private void increaseSnakeLength() {
        SnakePiece tail = snake.get(snake.size() - 1);
        CellPosition tailPos = tail.getPosition();
        SnakePiece newTail = new SnakePiece(new CellPosition(tailPos.getRow(), tailPos.getCol()));
        snake.add(newTail);
    }

    /**
     * Resets the game to the starting state.
     */
    public void resetGame() {
        snake.clear();
        snake.addAll(createInitialSnake());
        food = createFood();
        gameState = GameState.ACTIVE_GAME;
    }

    /**
     * Toggles between game and pause mode.
     */
    public void togglePause() {
        if (gameState == GameState.ACTIVE_GAME) {
            gameState = GameState.PAUSED;
        } else if (gameState == GameState.PAUSED) {
            gameState = GameState.ACTIVE_GAME;
        }
    }
}
