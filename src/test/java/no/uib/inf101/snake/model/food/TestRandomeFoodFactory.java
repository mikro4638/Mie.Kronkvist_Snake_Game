package no.uib.inf101.snake.model.food;
import no.uib.inf101.grid.CellPosition;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


// Denne testklassen tester RandomFoodFactory-klassen ved å opprette flere matobjekter og sjekke om de blir plassert innenfor
// riktig område på spillebrettet. Hvert matobjekt blir opprettet ved hjelp av RandomFoodFactory.createFood()-metoden, og deretter
// blir posisjonen til hvert matobjekt sjekket for å sikre at det ligger innenfor de gitte dimensjonene til spillebrettet.
public class TestRandomeFoodFactory {
    // Testmetoden testCreateFood() tester RandomFoodFactory.createFood()-metoden ved å opprette flere matobjekter og
    // verifisere at de blir plassert innenfor de gitte dimensjonene til spillebrettet.
    @Test
    public void testCreateFood() {
        int boardWidth = 10; // Bredde på spillebrettet
        int boardHeight = 10; // Høyde på spillebrettet
        RandomeFoodFactory factory = new RandomeFoodFactory(boardWidth, boardHeight); // Oppretter en RandomFoodFactory

        // Oppretter flere matobjekter og sjekker om de er innenfor brettets grenser
        for (int i = 0; i < 1000; i++) {
            Food food = factory.createFood(); // Oppretter matobjektet
            CellPosition position = food.getPosition(); // Henter posisjonen til matobjektet
            assertTrue(position.getRow() >= 0 && position.getRow() < boardHeight); // Sjekker om raden er innenfor grensene
            assertTrue(position.getCol() >= 0 && position.getCol() < boardWidth); // Sjekker om kolonnen er innenfor grensene
        }
    }
}