package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sample.board.Board;

public class Packman extends Unit {

    private boolean isMoving = false;
    private int speed = SIZE / 30 + 1;
    private int destX;
    private int destY;
    private int mouseAngle = board.rand.nextInt(45);
    ;
    private int mouseAngleStep = 1;

    public Packman(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
        destX = x;
        destY = y;
        board.setPackman(this);
    }

    @Override
    public void draw() {
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        double x = getShiftedX(0.1);
        double y = getShiftedY(0.1);
        double size = getShiftedSize(0.1);

        gc.fillArc(x, y, size, size, mouseAngle, 360 - mouseAngle * 2, ArcType.ROUND);
        gc.strokeArc(x, y, size, size, mouseAngle, 360 - mouseAngle * 2, ArcType.ROUND);

        mouseAngle += mouseAngleStep;
        if (mouseAngle <= 0) {
            mouseAngleStep = 1;
        }
        if (mouseAngle >= 45) {
            mouseAngleStep = -1;
        }

    }

    @Override
    public void move() {
        if (isMoving) {
            makeStep();
        }
    }

    private void makeStep() {

        if (Math.abs(x - destX) <= speed) {
            x = destX;
            xCell = x / SIZE;
        } else {
            if (x > destX) {
                x -= speed;
            } else {
                x += speed;
            }
        }

        if (Math.abs(y - destY) <= speed) {
            y = destY;
            yCell = y / SIZE;
        } else {
            if (y > destY) {
                y -= speed;
            } else {
                y += speed;
            }
        }

        if (x == destX && y == destY) {
            isMoving = false;
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
    public double getRadius() {
        return super.getRadius() * 0.9;
    }
}
