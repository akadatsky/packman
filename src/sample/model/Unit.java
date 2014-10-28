package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sample.board.Board;

public abstract class Unit {

    public static final int SIZE = 60;

    protected GraphicsContext gc;
    protected Board board;
    protected int xCell;
    protected int yCell;
    protected int x;
    protected int y;

    public Unit(GraphicsContext gc, Board board, int xCell, int yCell) {
        this.gc = gc;
        this.board = board;
        this.xCell = xCell;
        this.yCell = yCell;
        this.x = xCell * SIZE;
        this.y = yCell * SIZE;
    }

    public abstract void draw();

    public void move() {

    }

    public void keyPressed(KeyCode code) {

    }

    public int getXCell() {
        return xCell;
    }

    public int getYCell() {
        return yCell;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected double getShiftedX(double shift) {
        return x + SIZE * shift;
    }

    protected double getShiftedY(double shift) {
        return y + SIZE * shift;
    }

    public static double getShiftedSize(double shift) {
        return SIZE - 2 * SIZE * shift;
    }
}
