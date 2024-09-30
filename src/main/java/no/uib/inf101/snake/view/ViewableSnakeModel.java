package no.uib.inf101.snake.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.snake.model.GameState;
import no.uib.inf101.snake.model.snake.SnakePiece;

import no.uib.inf101.snake.model.food.Food;

import java.util.List;

public interface ViewableSnakeModel {
    
    /**
     * Returnerer dimensjonene til brettet, det vil si antall rader og kolonner.
     *
     * @return et objekt av typen GridDimension
     */
    GridDimension getDimension();


    /**
 * Et objekt som, når det itereres over, returnerer alle posisjoner
 * og tilhørende verdier.
 *
 * @return et itererbart objekt
 */

    Iterable<GridCell<Integer>> getTilesOnBoard();

    /**
 * Forteller oss tilstanden til spillet.
 *
 * @return tilstanden til spillet
 */
GameState getGameState();

/**
 * Returnerer posisjonen til slangens hode som en liste av SnakePiece-objekter.
 *
 * @return posisjonen til slangens hode
 */
List<SnakePiece> getSnake();

/**
 * Returnerer posisjonen til maten på brettet.
 *
 * @return posisjonen til maten
 */
Food getFood();

}
