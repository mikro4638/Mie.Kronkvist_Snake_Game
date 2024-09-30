package no.uib.inf101.snake.view;

import java.awt.Color;

/**
 * ColorTheme-grensesnittet definerer metoder for å hente forskjellige farger
 * som brukes i Snake-spillet.
 */
public interface ColorTheme {
    
    /**
     * Fargen til en gitt verdi
     *
     * @param value verdien som skal finne fargen til
     * @return en farge
     */
    Color getCellColor(Integer value); // Endret parameter fra Character til Integer

    /**
     * Fargen til rammen
     *
     * @return en farge
     */
    Color getFrameColor();

    /**
     * Bakgrunnsfargen
     *
     * @return bakgrunnsfargen
     */
    Color getBackgroundColor();

    /**
     * Tekstfargen i popup-vinduer
     *
     * @return en farge
     */
    Color getPopUpColor();
    
    /**
     * Tekstfargen
     *
     * @return en farge
     */
    Color getTextColor(); 
}
