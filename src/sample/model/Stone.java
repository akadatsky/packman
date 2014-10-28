package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.board.Board;

public class Stone extends Unit {
    public Stone(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
    }

    @Override
    public void draw() {
        gc.setFill(Color.GRAY);

        double x = getShiftedX(0.05);
        double y = getShiftedY(0.05);
        double size = getShiftedSize(0.05);

        gc.fillRoundRect(x, y, size, size, 15, 15);
    }

    @Override
    public void move() {

    }
}
