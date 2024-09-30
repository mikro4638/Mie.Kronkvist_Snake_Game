package no.uib.inf101.snake.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import no.uib.inf101.snake.model.SnakeModel;
import no.uib.inf101.snake.model.GameState;
import no.uib.inf101.snake.view.SnakeView;
import no.uib.inf101.snake.model.SnakeBoard;

// Testklasse for SnakeController
public class SnakeControllerTest {

    // Test for å verifisere tilbakestilling av spillet
    @Test
    public void testGameReset() {
        // Opprett en SnakeModel og SnakeView
        SnakeModel model = new SnakeModel(new SnakeBoard(10, 10));
        SnakeView view = new SnakeView(model);

        // Opprett en SnakeController
        SnakeController controller = new SnakeController(model, view);

        // Simuler spillet over ved å trykke enter-tasten
        KeyEvent enterKeyEvent = new KeyEvent(view, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        controller.keyPressed(enterKeyEvent);

        // Verifiser at spillet er i aktiv tilstand etter tilbakestilling
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());
    }

    // Test for å verifisere økning av hastighet
    @Test
    public void testIncreaseSpeed() {
        // Opprett en SnakeModel og SnakeView
        SnakeModel model = new SnakeModel(new SnakeBoard(10, 10));
        SnakeView view = new SnakeView(model);

        // Opprett en SnakeController
        SnakeController controller = new SnakeController(model, view);

        // Sett poengsum til 5 for å utløse hastighetsøkning
        model.setScore(5); // Bruk setScore for å sette poengene
        int initialInterval = controller.getTimer().getDelay(); // Få opprinnelig hastighet

        // Utløs hastighetsøkning
        controller.increaseSpeed();

        // Verifiser at hastigheten har økt
        assertTrue(initialInterval > controller.getTimer().getDelay());
    }

    // Test for å verifisere pausefunksjonalitet
    @Test
    public void testPauseGame() {
        // Opprett en SnakeModel og SnakeView
        SnakeModel model = new SnakeModel(new SnakeBoard(10, 10));
        SnakeView view = new SnakeView(model);
    
        // Opprett en SnakeController
        SnakeController controller = new SnakeController(model, view);
    
        // Forsikre deg om at spillet er i aktiv tilstand i utgangspunktet
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());
    
        // Trykk på mellomromstasten for å bytte pause
        controller.spacePressed();
    
        // Verifiser at spillet nå er i pausemodus
        assertEquals(GameState.PAUSED, model.getGameState());
    }

}
