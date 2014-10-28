package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.board.Board;
import sample.model.Unit;

public class Main extends Application {

    public static final int CANVAS_X = Unit.SIZE * 11;
    public static final int CANVAS_Y = Unit.SIZE * 9;

    private GraphicsContext gc;
    private Board board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Packman");
        Canvas canvas = new Canvas(CANVAS_X, CANVAS_Y);
        BorderPane group = new BorderPane();
        group.setCenter(canvas);
        final Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();

        registerOnKeyPressListener(primaryStage.getScene());

        initGame();
        runDrawThread();
    }


    private void initGame() {
        board = new Board(gc);
    }

    private void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRoundRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight(), 0, 0);
        board.draw();
        board.move();
    }

    private void runDrawThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                runThreadBody();
            }
        };
        thread.start();
    }

    private void runThreadBody() {
        while (true) {
            showChangesInGuiThread();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void showChangesInGuiThread() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                drawFrame();
            }
        });
    }

    public void registerOnKeyPressListener(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                board.keyPressed(event.getCode());
            }
        });
    }


}
