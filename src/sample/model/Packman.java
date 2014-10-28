package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sample.board.Board;

public class Packman extends MovingUnit {

    public Packman(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
        board.setPackman(this);
    }

    @Override
    public void draw() {
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        drawPacman();
    }

    @Override
    public void move() {
        if (isMoving) {
            makeStep();
        }
    }

    @Override
    public void keyPressed(KeyCode code) {
        int tmpDestX = xCell * SIZE;
        int tmpDestY = yCell * SIZE;
        switch (code) {
            case UP:
                if (destX != tmpDestX) {
                    return;
                }
                if (board.canMove(this, Board.Side.TOP)) {
                    tmpDestY -= SIZE;
                }
                break;
            case RIGHT:
                if (destY != tmpDestY) {
                    return;
                }
                if (board.canMove(this, Board.Side.RIGHT)) {
                    tmpDestX += SIZE;
                }
                break;
            case DOWN:
                if (destX != tmpDestX) {
                    return;
                }
                if (board.canMove(this, Board.Side.BOTTOM)) {
                    tmpDestY += SIZE;
                }
                break;
            case LEFT:
                if (destY != tmpDestY) {
                    return;
                }
                if (board.canMove(this, Board.Side.LEFT)) {
                    tmpDestX -= SIZE;
                }
                break;
        }
        destX = tmpDestX;
        destY = tmpDestY;
        isMoving = true;
    }

    @Override
    public int getSpeed() {
        return SIZE / 30 + 1;
    }
}
