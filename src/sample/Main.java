package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.board.Board;

public class Main extends Application {

    private static final int PAUSE_BETWEEN_FRAMES_MILLIS = 40;

    private Board board;
    private boolean closed = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pacman");
        Canvas canvas = new Canvas();
        BorderPane group = new BorderPane();

        initGame(canvas);

        group.setCenter(canvas);
        Scene scene = new Scene(group);
        scene.setOnKeyPressed(event -> board.keyPressed(event.getCode()));
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(this::runMainGameLoopInThread).start();
    }

    @Override
    public void stop() throws Exception {
        closed = true;
    }


    private void initGame(Canvas canvas) {
        board = new Board(canvas.getGraphicsContext2D());
        canvas.setWidth(Config.CELL_SIZE * board.getColumnCount());
        canvas.setHeight(Config.CELL_SIZE * board.getLineCount());
    }

    private void runMainGameLoopInThread() {
        while (!closed) {
            // run in UI thread
            Platform.runLater(this::drawFrame);
            try {
                Thread.sleep(PAUSE_BETWEEN_FRAMES_MILLIS);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void drawFrame() {
        board.clean();
        board.draw();
        board.move();
    }

}
