package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.board.Board;

public class Main extends Application {

    private static final int FRAME_MILLIS = 40;

    private GraphicsContext gc;
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

        new Thread(this::runGameLoopInThread).start();
    }

    @Override
    public void stop() throws Exception {
        closed = true;
    }


    private void initGame(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        board = new Board(gc);
        canvas.setWidth(Config.CELL_SIZE * board.getColumnCount());
        canvas.setHeight(Config.CELL_SIZE * board.getLineCount());
    }

    private void runGameLoopInThread() {
        while (!closed) {
            // run in UI thread
            Platform.runLater(this::drawFrame);
            try {
                Thread.sleep(FRAME_MILLIS);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRoundRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight(), 0, 0);
        board.draw();
        board.move();
    }

}
