package sample.model;

import javafx.scene.canvas.GraphicsContext;
import sample.board.Board;

public class EmptyCell extends Unit {

    public EmptyCell(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
    }

    @Override
    public void draw() {

    }

    @Override
    public void move() {

    }
}
