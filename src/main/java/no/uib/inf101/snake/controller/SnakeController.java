package no.uib.inf101.snake.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import no.uib.inf101.snake.model.SnakeModel;
import no.uib.inf101.snake.view.SnakeView;
import no.uib.inf101.snake.model.GameState;

/**
 * Controller class that handles user input and manages game logic for the snake game.
 * This class implements the KeyListener interface to respond to key events.
 */
public class SnakeController implements KeyListener {

    private static SnakeController instance;
    private SnakeModel model;
    private Timer timer;
    private int dx = 0;
    private int dy = 0;
    private static final int INITIAL_INTERVAL = 300;
    private int interval = INITIAL_INTERVAL;
    private int lastScore = 0;

    /**
     * Public constructor to prevent direct instantiation of SnakeController.
     *
     * @param model The SnakeModel used for game logic
     * @param view The SnakeView used for displaying the game
     */
    public SnakeController(SnakeModel model, SnakeView view) {
        this.model = model;
        view.addKeyListener(this);

        this.timer = new Timer(interval, e -> {
            if (model.getGameState() == GameState.ACTIVE_GAME) {
                model.moveSnake(dx, dy);
                view.repaint();
            }
        });
        timer.start();
    }

    /**
     * Public static method to retrieve an instance of SnakeController.
     *
     * @param model The SnakeModel used for game logic
     * @param view The SnakeView used for displaying the game
     * @return The singleton instance of SnakeController
     */
    public static SnakeController getInstance(SnakeModel model, SnakeView view) {
        if (instance == null) {
            instance = new SnakeController(model, view);
        }
        return instance;
    }

    /**
     * Public static method to retrieve an instance of SnakeController.
     *
     * @return The singleton instance of SnakeController
     * @throws IllegalStateException if SnakeController has not been initialized with SnakeModel and SnakeView
     */
    public static SnakeController getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SnakeController must be initialized with SnakeModel and SnakeView first");
        }
        return instance;
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                model.resetGame();
                resetSnakeDirection();
            }
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                dx = 0; dy = -1; break;
            case KeyEvent.VK_RIGHT:
                dx = 0; dy = 1; break;
            case KeyEvent.VK_DOWN:
                dx = 1; dy = 0; break;
            case KeyEvent.VK_UP:
                dx = -1; dy = 0; break;
        }

        // Check if the score has changed, and increase speed if necessary
        if (model.getScore() > lastScore) {
            lastScore = model.getScore();
            increaseSpeed();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Resets the snake's direction.
     */
    private void resetSnakeDirection() {
        dx = 0;
        dy = 0; // Reset x and y changes to stop snake movement
    }

    /**
     * Increases the speed of the snake based on the current score.
     */
    public void increaseSpeed() {
        int currentScore = model.getScore();

        // Check if the score is a multiple of 5 and within a certain range
        if (currentScore % 5 == 0 && currentScore >= 5) {
            if (currentScore < 10) {
                interval = 250; 
            } else if (currentScore < 15) {
                interval = 200; 
            }
        }

        // Ensure the speed does not become zero or negative
        if (interval <= 0) {
            interval = 1; 
        }

        timer.setDelay(interval);
        System.out.println("Hastigheten har endret seg! Ny hastighet: " + interval + " ms");
    }

    /**
     * Returns the timer for the controller.
     *
     * @return The timer for the controller
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Resets the speed to the initial level.
     */
    public void resetSpeed() {
        interval = INITIAL_INTERVAL; // Reset to initial interval
        timer.setDelay(interval); // Update the timer's interval
    }

    /**
     * Method that handles the pause state.
     */
    public void spacePressed() {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.togglePause();
            if (model.getGameState() == GameState.GAME_OVER) {
                timer.stop();
            } else {
                timer.start();
            }
        }
    }
}
