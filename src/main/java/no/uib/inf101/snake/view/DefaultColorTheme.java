package no.uib.inf101.snake.view;

import java.awt.Color;

/**
 * DefaultColorTheme-klassen implementerer ColorTheme-grensesnittet og gir standardfarger
 * for visse elementer i Snake-spillet.
 */
public class DefaultColorTheme implements ColorTheme {

    /**
     * Returnerer fargen til en gitt verdi.
     *
     * @param value verdien som skal finne fargen til
     * @return en farge
     * @throws IllegalArgumentException hvis det ikke finnes en farge for den gitte verdien
     */
    @Override
    public Color getCellColor(Integer value) {
        switch (value) {
            case 0:
                return Color.BLACK; // Farge for tom celle
            case 1:
                return Color.RED; // Farge for hindringscelle
            
            case -1:
                return Color.GREEN;
            default:
                throw new IllegalArgumentException("No available color for value: " + value);
        }
    }

    /**
     * Returnerer fargen til rammen.
     *
     * @return en farge
     */
    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0);
    }

    /**
     * Returnerer bakgrunnsfargen.
     *
     * @return bakgrunnsfargen
     */
    @Override
    public Color getBackgroundColor() {
        return Color.BLACK; // Bakgrunnsfargen satt til svart
    }

    /**
     * Returnerer fargen til tekst i popup-vinduer.
     *
     * @return en farge
     */
    @Override
    public Color getPopUpColor() {
        return new Color(0, 0, 0, 128);
    }

    /**
     * Returnerer tekstfargen.
     *
     * @return en farge
     */
    @Override
    public Color getTextColor() {
        return Color.WHITE; // Tekstfargen satt til hvit
    }
}
