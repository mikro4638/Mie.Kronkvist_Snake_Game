package no.uib.inf101.snake.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.snake.model.GameState;
import no.uib.inf101.snake.model.SnakeModel;
import no.uib.inf101.snake.model.food.Food;
import java.awt.FontMetrics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import no.uib.inf101.snake.model.snake.SnakePiece;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * SnakeView representerer visningen av Snake-spillet.
 * Denne klassen håndterer tegning av spillebrettet, slangen, maten, score og meldinger til spilleren.
 * SnakeView implementerer også KeyListener for å håndtere brukerinput.
 */

public class SnakeView extends JPanel implements KeyListener {
    
// Konstante verdier for marger og foretrukket størrelse
    public static final int OUTERMARGIN = 15; // Ytre marg for panelet
    public static final int CELLMARGIN = 2; // Marg mellom cellene
    public static final int PREFERREDSIDESIZE = 30; // Foretrukket størrelse for cellene

    // Attributter for visningen
    private SnakeModel snakeModel; // Modell for slangespillet
    private ColorTheme colorTheme; // Fargetema for visningen
    private boolean welcomeScreen = true; // Viser velkomstskjermen
    private boolean isGamePaused = false; // Indikerer om spillet er satt på pause

    /**
     * Oppretter et nytt SnakeView-objekt.
     *
     * @param snakeModel et SnakeModel-objekt
     */

    public SnakeView(SnakeModel snakeModel) {
        this.snakeModel = snakeModel; // Setter modellen for slangespillet
        this.colorTheme = new DefaultColorTheme(); // Setter standard fargetema
        this.setBackground(colorTheme.getBackgroundColor()); // Setter bakgrunnsfargen
        this.setFocusable(true); // Setter fokus på panelet slik at det kan motta tastetrykk
        this.setPreferredSize(getDefaultSize(snakeModel.getDimension())); // Setter foretrukket størrelse for panelet
        this.addKeyListener(this); // Legger til en tastelytter
    }

    // Metode for å tegne komponentene i visningen
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Kaller superklassens paintComponent-metode
        Graphics2D g2 = (Graphics2D) g; // Konverterer Graphics-objektet til Graphics2D

        // Tegner spillkomponentene
        drawGame(g2);
    }

   /**
     * Tegner spillkomponentene.
     *
     * @param g2 en Graphics2D-kontekst
     */

    private void drawGame(Graphics2D g2) {
        // Definerer en rektangulær boks som skal inneholde spillkomponentene
        Rectangle2D box = new Rectangle2D.Double(
                OUTERMARGIN,
                OUTERMARGIN,
                this.getWidth() - OUTERMARGIN * 2,
                this.getHeight() - OUTERMARGIN * 2);
        g2.setColor(null); // Setter fargen til null

        // Fyller bakgrunnsboksen med en farge
        g2.fill(box);
    
        // Henter antall rader og kolonner fra spillmodellen
        int rows = snakeModel.getDimension().rows();
        int cols = snakeModel.getDimension().cols();
    
        // Beregner størrelsen på hver celle basert på spillmodellens dimensjoner og størrelsen på bakgrunnsboksen
        double cellWidth = box.getWidth() / cols;
        double cellHeight = box.getHeight() / rows;
    
        // Tegner mørke grå linjer mellom cellene
        g2.setColor(Color.DARK_GRAY);
        for (int i = 1; i < cols; i++) {
            double x = OUTERMARGIN + i * cellWidth;
            g2.drawLine((int) x, (int) (OUTERMARGIN), (int) x, (int) (OUTERMARGIN + box.getHeight()));
        }
        for (int i = 1; i < rows; i++) {
            double y = OUTERMARGIN + i * cellHeight;
            g2.drawLine((int) (OUTERMARGIN), (int) y, (int) (OUTERMARGIN + box.getWidth()), (int) y);
        }
    
        // Fortsett med å tegne mat og slange som før
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box,
                snakeModel.getDimension(), CELLMARGIN);
    
        Food food = snakeModel.getFood();
        if (food != null) {
            CellPosition foodPos = food.getPosition();
            Rectangle2D foodRect = converter.getBoundsForCell(foodPos);
            g2.setColor(Color.RED);
            g2.fill(foodRect);
        }
    
        List<SnakePiece> snake = snakeModel.getSnake();
        for (SnakePiece piece : snake) {
            CellPosition piecePos = piece.getPosition();
            Rectangle2D pieceRect = converter.getBoundsForCell(piecePos);
            g2.setColor(Color.GREEN);
            g2.fill(pieceRect);
        }
    
        // Tegner score og eventuelle popups
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        int initialSnakeSize = snakeModel.createInitialSnake().size();
        int currentScore = snake.size() - initialSnakeSize;
        String scoreText = "Score: " + (snakeModel.getGameState() == GameState.GAME_OVER ? 0 : currentScore);
        int scoreX = OUTERMARGIN;
        int scoreY = this.getHeight() - OUTERMARGIN;
        g2.drawString(scoreText, scoreX, scoreY);
    
        // Tegner game over-, velkomst- og pausemeldinger hvis nødvendig
        if (snakeModel.getGameState() == GameState.GAME_OVER) {
            drawGameOver(g2);
        } else if (welcomeScreen) {
            drawWelcomeScreen(g2);
        } else if (isGamePaused) {
            drawPauseScreen(g2);
        }
    }

    /**
     * Tegner spillslutt-meldingen.
     *
     * @param g2 en Graphics2D-kontekst
     */

    private void drawGameOver(Graphics2D g2) {
        // Definerer en rektangulær boks som skal inneholde "game over"-meldingen
        Rectangle2D box = new Rectangle2D.Double(
                0,
                0,
                this.getWidth(),
                this.getHeight());
        g2.setColor(colorTheme.getPopUpColor()); // Setter fargen til popup-boksen
        g2.fill(box); // Fyller popup-boksen med farge

        // Tegner "game over"-teksten
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Monospaced", Font.BOLD, 30));
        drawCenteredString(g2, "GAME OVER", (int) (box.getCenterY() - 40), box);

        // Tegner instruksjon for å starte nytt spill
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        drawCenteredString(g2, "Trykk ENTER for å spille igjen", (int) (box.getCenterY() + 20), box);
    }

    /**
     * Tegner velkomstmeldingen.
     *
     * @param g2 en Graphics2D-kontekst
     */

    private void drawWelcomeScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        drawCenteredString(g2, "Velkommen til Snake-spillet!", getHeight() / 2 - 20, getBounds());
        drawCenteredString(g2, "Trykk på en tast for å begynne.", getHeight() / 2 + 20, getBounds());
    }

    /**
     * Tegner pauseskjermen.
     *
     * @param g2 en Graphics2D-kontekst
     */

    private void drawPauseScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        drawCenteredString(g2, "PAUSE", getHeight() / 2 - 20, getBounds());
        drawCenteredString(g2, "Trykk på mellomromstasten for å fortsette.", getHeight() / 2 + 20, getBounds());
    }

    /**
     * Metode som beregner størrelsen på panelet basert på spillets dimensjon.
     *
     * @param gd spillets dimensjon
     * @return en Dimension-objekt som representerer den foretrukne størrelsen
     */

    private static Dimension getDefaultSize(GridDimension gd) {
        int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN);
        return new Dimension(width, height);
    }

    // Metode som håndterer tastetrykk
    @Override
    public void keyPressed(KeyEvent e) {
        // Håndterer tastetrykk for velkomstskjerm, aktivt spill og pause
        if (welcomeScreen) {
            welcomeScreen = false; // Skjuler velkomstskjermen
            repaint(); // Oppdaterer visningen
        } else if (snakeModel.getGameState() == GameState.ACTIVE_GAME || snakeModel.getGameState() == GameState.PAUSED) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) { // Hvis mellomromstasten trykkes
                snakeModel.togglePause(); // Bytter mellom pause og spill
                isGamePaused = (snakeModel.getGameState() == GameState.PAUSED); // Oppdaterer spillstatus
                repaint(); // Oppdaterer visningen
            } else {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && snakeModel.getGameState() == GameState.GAME_OVER) {
                    snakeModel.resetGame(); // Starter nytt spill
                    welcomeScreen = true; // Viser velkomstskjermen
                    isGamePaused = false; // Setter spillstatus til ikke-pause
                    repaint(); // Oppdaterer visningen
                }
            }
        }
    }

    // Metode for tastetrykk 
    @Override
    public void keyTyped(KeyEvent e) {}

    // Metode for tastetrykk 
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Tegner en tekststreng sentrert i en rektangel.
     *
     * @param g2 en Graphics2D-kontekst
     * @param text teksten som skal tegnes
     * @param centerY Y-posisjonen for teksten
     * @param bounds grensene til rektangelet
     */

    private void drawCenteredString(Graphics2D g2, String text, int centerY, Rectangle2D bounds) {
        FontMetrics fm = g2.getFontMetrics(); // Henter fontmetrikker
        int x = (int) ((bounds.getWidth() - fm.stringWidth(text)) / 2); // Beregner x-posisjon for teksten
        int y = centerY; // Y-posisjon for teksten
        g2.drawString(text, x, y); // Tegner teksten
    }
}