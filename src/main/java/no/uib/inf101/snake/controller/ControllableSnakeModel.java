package no.uib.inf101.snake.controller;

import no.uib.inf101.snake.model.GameState;

/**
 * An interface defining the functions needed to control the snake in the game.
 * This interface abstracts how to control the snake, handling the logic for snake movements,
 * scoring, and game state by the implementing class.
 */
public interface ControllableSnakeModel {

    /**
     * Moves the snake on the board.
     *
     * @param dx the change in the x-coordinate
     * @param dy the change in the y-coordinate
     * @return true if the move was successful, otherwise false
     *         Returns true if the snake could move in the desired direction without hitting walls
     *         or itself. Returns false if the movement was not possible.
     */
    boolean moveSnake(int dx, int dy);

    /**
     * Returns the current score in the game.
     *
     * @return the current score
     *         The current score in the game, which increases when the snake eats food or achieves other goals.
     */
    int getScore();

    /**
     * Provides the current state of the game.
     *
     * @return the state of the game
     *         The state of the game, which can be "ongoing", "finished", or "paused".
     *         "Ongoing" indicates that the game is in progress, "finished" indicates that the game is over,
     *         and "paused" indicates that the game is temporarily stopped.
     */
    GameState getGameState();
}
