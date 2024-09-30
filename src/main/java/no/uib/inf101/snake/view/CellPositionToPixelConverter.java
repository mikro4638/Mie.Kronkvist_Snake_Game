package no.uib.inf101.snake.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * CellPositionToPixelConverter konverterer en celleposisjon til piksler
 * i et rektangel som representerer et cellebrett.
 */
public class CellPositionToPixelConverter {

    private final Rectangle2D box; // Rektangel som representerer et cellebrett
    private final GridDimension gd; // Dimensjonene til cellebrettet
    private final double margin; // Marginalavstand rundt cellene

    /**
     * Konstruktør for CellPositionToPixelConverter.
     * 
     * @param box     Rektangel som representerer et cellebrett
     * @param gd      Dimensjonene til cellebrettet
     * @param margin  Marginalavstand rundt cellene
     */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box; // Angi rektangelet som representerer brettet
        this.gd = gd; // Angi dimensjonene til brettet
        this.margin = margin; // Angi margen rundt cellene
    }

    /**
     * Beregner og returnerer grensene for en celle gitt dens posisjon på brettet.
     * 
     * @param cellPosition Posisjonen til cellen
     * @return et rektangel som representerer grensene for cellen i piksler
     */
    public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
        // Beregn bredden og høyden til en celle
        double cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
        double cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
        // Beregn x- og y-koordinater for cellen
        double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
        double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
        // Opprett og returner et rektangel som representerer cellens grenser
        return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
    }
}
