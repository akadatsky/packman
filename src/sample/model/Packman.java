package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sample.board.Board;

public class Packman extends Unit {

    private int mouseAngle = board.rand.nextInt(45);;
    private int mouseAngleStep = 1;

    public Packman(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
    }

    @Override
    public void draw() {
        gc.setFill(Color.YELLOW);
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

    }

    @Override
    public void move() {
    }

    @Override
    public void keyPressed(KeyCode code) {
        switch (code) {
            case UP:
                break;
            case RIGHT:
                break;
            case DOWN:
                break;
            case LEFT:
                break;
        }
    }
}
