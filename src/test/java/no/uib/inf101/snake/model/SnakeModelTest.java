package no.uib.inf101.snake.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.snake.model.snake.SnakePiece;
import no.uib.inf101.snake.model.food.Food;
import java.util.List;


// Testklasse for SnakeModel
public class SnakeModelTest {

    // Test for å sjekke gyldig bevegelse
    @Test
    public void testValidMove() {
        // Opprett et SnakeBoard og en SnakeModel
        SnakeBoard board = new SnakeBoard(10, 10);
        SnakeModel snakeModel = new SnakeModel(board);
        
        // Forvent at slangen kan bevege seg oppover
        assertTrue(snakeModel.moveSnake(-1, 0)); // Moving snake upwards
    }

    // Test for kollisjon med vegg
    @Test
    public void testCollisionWithWall() {
        // Opprett et SnakeBoard og en SnakeModel
        SnakeBoard board = new SnakeBoard(10, 10);
        SnakeModel snakeModel = new SnakeModel(board);
        
        // Forvent at slangen kolliderer med veggen ved bevegelse opp og venstre
        assertFalse(snakeModel.moveSnake(-1, -5)); // Moving snake upwards and left, should collide with wall
        assertEquals(GameState.GAME_OVER, snakeModel.getGameState());
    }
    
    // Test for kollisjon med mat
    @Test
    public void testFoodCollision() {
        // Opprett et SnakeBoard og en SnakeModel
        SnakeBoard board = new SnakeBoard(10, 10);
        SnakeModel snakeModel = new SnakeModel(board);
        // Hent maten
        Food food = snakeModel.getFood();
        // Hent posisjonen til maten
        CellPosition foodPos = food.getPosition();
    
        // Hent posisjonen til hodet til slangen
        CellPosition headPos = snakeModel.getSnake().get(0).getPosition();
        // Beregn deltaRow og deltaCol for å flytte slangen til matposisjonen
        int deltaRow = foodPos.getRow() - headPos.getRow();
        int deltaCol = foodPos.getCol() - headPos.getCol();
        // Flytt slangen til matposisjonen
        snakeModel.moveSnake(deltaRow, deltaCol);
    
        // Sjekk om slangen har spist maten
        assertEquals(foodPos, snakeModel.getSnake().get(0).getPosition());
        // Forvent at slangelengden øker med 1 etter å ha spist maten
        assertEquals(4, snakeModel.getSnake().size());
    }

    // Test for kollisjon med seg selv
    @Test
    public void testSelfCollision() {
        // Opprett et SnakeBoard og en SnakeModel
        SnakeBoard board = new SnakeBoard(10, 10);
        SnakeModel snakeModel = new SnakeModel(board);
    
        // Legg til ekstra slangedeler for å øke sannsynligheten for kollisjon
        List<SnakePiece> snake = snakeModel.getSnake();
        snake.add(new SnakePiece(new CellPosition(8, 6))); // Legg til en ekstra slangedel nærmere hodet
        snake.add(new SnakePiece(new CellPosition(8, 5))); // Legg til en ekstra slangedel nærmere hodet
    
        // Flytt hodet til en posisjon hvor kollisjon med kroppen er uunngåelig
        snake.get(0).setPosition(new CellPosition(8, 6)); // Sett hodet til en posisjon nær den andre slangedelen
    
        // Flytt slangen (prøver å bevege seg oppover), og forvent at den kolliderer med seg selv
        assertFalse(snakeModel.moveSnake(0, -1)); // Moving snake upwards, should collide with itself
        assertEquals(GameState.GAME_OVER, snakeModel.getGameState());
    }

    // Test for å sjekke at maten blir spist
    @Test
    public void testFoodEaten() {
        // Opprett et SnakeBoard og en SnakeModel
        SnakeBoard board = new SnakeBoard(10, 10);
        SnakeModel snakeModel = new SnakeModel(board);
        
        // Hent opprinnelig lengde på slangen
        int originalLength = snakeModel.getSnake().size();
        
        // Hent posisjonen til maten
        Food food = snakeModel.getFood();
        CellPosition foodPos = food.getPosition();
        
        // Flytt slangen til matposisjonen
        CellPosition headPos = snakeModel.getSnake().get(0).getPosition();
        int deltaRow = foodPos.getRow() - headPos.getRow();
        int deltaCol = foodPos.getCol() - headPos.getCol();
        snakeModel.moveSnake(deltaRow, deltaCol);
        
        // Sjekk om slangen har økt i lengde
        assertEquals(originalLength + 1, snakeModel.getSnake().size());
        
        // Sjekk at maten er plassert på en ny posisjon etter at den er spist
        assertNotEquals(foodPos, snakeModel.getFood().getPosition());
    }
}
