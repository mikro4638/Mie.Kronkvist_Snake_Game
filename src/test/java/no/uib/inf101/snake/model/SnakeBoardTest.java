package no.uib.inf101.snake.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// Testklasse for SnakeBoard
public class SnakeBoardTest {

    // Test for å verifisere opprettelsen av SnakeBoard
    @Test
    public void testSnakeBoardCreation() {
        // Angi antall rader og kolonner
        int rows = 10;
        int cols = 10;

        // Opprett et SnakeBoard-objekt
        SnakeBoard snakeBoard = new SnakeBoard(rows, cols);
        
        // Sjekk om antall rader og kolonner er riktig
        assertEquals(rows, snakeBoard.rows());
        assertEquals(cols, snakeBoard.cols());
    }
}
