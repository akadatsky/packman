package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import sample.board.Board;

public abstract class MovingUnit extends Unit {

    protected boolean isMoving = false;
    protected int destX;
    protected int destY;
    protected int mouseAngle = board.rand.nextInt(45);
    protected int mouseAngleStep = 1;

    public MovingUnit(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
        destX = x;
        destY = y;
    }

    public abstract int getSpeed();

    protected void drawPacman(){
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
    public double getRadius() {
        return super.getRadius() * 0.9;
    }

    protected void makeStep() {
        if (Math.abs(x - destX) <= getSpeed()) {
            x = destX;
            xCell = x / SIZE;
        } else {
            if (x > destX) {
                x -= getSpeed();
            } else {
                x += getSpeed();
            }
        }

        if (Math.abs(y - destY) <= getSpeed()) {
            y = destY;
            yCell = y / SIZE;
        } else {
            if (y > destY) {
                y -= getSpeed();
            } else {
                y += getSpeed();
            }
        }

        if (x == destX && y == destY) {
            isMoving = false;
        }
    }


}
