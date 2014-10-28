package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sample.board.Board;

public class Enemy extends Unit {

    public Enemy(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
    }

    @Override
    public void draw() {
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        double x = getShiftedX(0.1);
        double y = getShiftedY(0.1);
        double size = getShiftedSize(0.1);

        gc.fillArc(x, y, size, size, 45, 280, ArcType.ROUND);
        gc.strokeArc(x, y, size, size, 45, 280, ArcType.ROUND);
    }

    @Override
    public void move() {
    }
}
