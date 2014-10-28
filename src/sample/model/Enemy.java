package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sample.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Unit {

    private boolean isMoving = false;
    private int speed = SIZE / 60 + 1;
    private int destX;
    private int destY;
    private int mouseAngle = board.rand.nextInt(45);
    private int mouseAngleStep = 1;

    public Enemy(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
        destX = x;
        destY = y;
    }

    @Override
    public void draw() {
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        double x = getShiftedX(0.1);
        double y = getShiftedY(0.1);
        double size = getShiftedSize(0.1);

        gc.fillArc(x, y, size, size, mouseAngle, 360 - mouseAngle*2, ArcType.ROUND);
        gc.strokeArc(x, y, size, size, mouseAngle, 360 - mouseAngle*2, ArcType.ROUND);

        mouseAngle += mouseAngleStep;
        if (mouseAngle <= 0) {
            mouseAngleStep = 1;
        }
        if (mouseAngle >= 45) {
            mouseAngleStep = -1;
        }

        if (board.isTouchToPackman(this)){
            board.setGameOver();
        }


    }

    @Override
    public void move() {

        if (isMoving) {
            makeStep();
            return;
        }

        List<Board.Side> possibleMoves = new ArrayList<Board.Side>();
        for (Board.Side side : Board.Side.values()) {
            if (board.canMove(this, side)) {
                possibleMoves.add(side);
            }
        }
        if (possibleMoves.size() > 0) {
            Board.Side side = possibleMoves.get(board.rand.nextInt(possibleMoves.size()));
            destX = xCell * SIZE;
            destY = yCell * SIZE;
            switch (side) {
                case TOP:
                    destY -= SIZE;
                    break;
                case RIGHT:
                    destX += SIZE;
                    break;
                case BOTTOM:
                    destY += SIZE;
                    break;
                case LEFT:
                    destX -= SIZE;
                    break;
            }
            isMoving = true;
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
    public double getRadius() {
        return super.getRadius() * 0.9;
    }


}
