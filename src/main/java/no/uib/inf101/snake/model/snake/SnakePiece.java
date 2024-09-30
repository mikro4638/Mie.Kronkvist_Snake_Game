package no.uib.inf101.snake.model.snake;

import no.uib.inf101.grid.CellPosition;

/**
 * En brikke som utgjør en del av slangen.
 */
public class SnakePiece {
    private CellPosition position; // Posisjonen til brikken

    /**
     * Oppretter en ny SnakePiece med en gitt posisjon.
     *
     * @param position posisjonen til brikken
     */
    public SnakePiece(CellPosition position) {
        this.position = position; // Setter startposisjonen til brikken
    }

    /**
     * Henter posisjonen til brikken.
     *
     * @return posisjonen til brikken
     */
    public CellPosition getPosition() {
        return position;
    }

    /**
     * Oppdaterer posisjonen til brikken.
     *
     * @param newPosition den nye posisjonen til brikken
     */
    public void setPosition(CellPosition newPosition) {
        this.position = newPosition;
    }
}
