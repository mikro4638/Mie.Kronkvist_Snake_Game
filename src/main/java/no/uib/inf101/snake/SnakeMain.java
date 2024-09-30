package no.uib.inf101.snake;

import javax.swing.JFrame;

import no.uib.inf101.snake.controller.SnakeController;
import no.uib.inf101.snake.model.SnakeBoard;
import no.uib.inf101.snake.model.SnakeModel;
import no.uib.inf101.snake.view.SnakeView;
import no.uib.inf101.snake.midi.SnakeSong;

/**
 * SnakeMain er inngangspunktet for å kjøre slangespillet.
 * Den initialiserer spillets komponenter og starter spill-loopen.
 */
public class SnakeMain {

    /** Tittelen på spillvinduet. */
    public static final String WINDOW_TITLE = "INF101 Snake";

    /**
     * main-metoden initialiserer og kjører slangespillet.
     *
     * @param args kommandolinje-argumentene (ikke brukt)
     */
    public static void main(String[] args) {
        SnakeBoard board = new SnakeBoard(20, 15);
        SnakeModel model = new SnakeModel(board);
        SnakeView view = new SnakeView(model);
        new SnakeController(model, view);
        SnakeSong music = new SnakeSong();
        music.run();
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }

}
