package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.board.Board;

public class Gold extends Unit {

    private boolean isVisible = true;

    public Gold(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
        board.incGold();
    }

    @Override
    public void draw() {

        if (!isVisible){
            return;
        }

        gc.setFill(Color.GOLD);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        double x = getShiftedX(0.3);
        double y = getShiftedY(0.3);
        double size = getShiftedSize(0.3);

        gc.fillOval(x, y, size, size);
        gc.strokeOval(x, y, size, size);

        if (board.isTouchToPackman(this)){
            board.decGold();
            isVisible = false;
        }

    }

    @Override
    public double getRadius() {
        return super.getRadius() * 0.7;
    }


}
