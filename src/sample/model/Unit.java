package sample.model;

import javafx.scene.canvas.GraphicsContext;
import sample.board.Board;

public abstract class Unit {

    protected final static int SIZE = 60;

    protected GraphicsContext gc;
    protected Board board;
    protected int xCell;
    protected int yCell;

    public Unit(GraphicsContext gc, Board board, int xCell, int yCell) {
        this.gc = gc;
        this.board = board;
        this.xCell = xCell;
        this.yCell = yCell;
    }

    public abstract void draw();

    public abstract void move();

    protected int getX() {
        return xCell * SIZE;
    }

    protected int getY() {
        return yCell * SIZE;
    }


    protected double getShiftedX(double shift) {
        return xCell * SIZE + SIZE * shift;
    }

    protected double getShiftedY(double shift) {
        return yCell * SIZE + SIZE * shift;
    }

    public static double getShiftedSize(double shift) {
        return SIZE - 2 * SIZE * shift;
    }
}
