package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sample.Config;
import sample.board.Board;

public abstract class Unit {

    public static final int SIZE = Config.CELL_SIZE;

    protected GraphicsContext gc;
    protected Board board;
    protected int xCell;
    protected int yCell;
    protected int x;
    protected int y;
    protected double radius = SIZE / 2.0;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getXCenter() {
        return x + radius;
    }

    public double getYCenter() {
        return y + radius;
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

    public double getRadius() {
        return radius;
    }
}
