package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Config;
import sample.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends MovingUnit {

    public Enemy(GraphicsContext gc, Board board, int xCell, int yCell) {
        super(gc, board, xCell, yCell);
    }

    @Override
    public void draw() {
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        drawPacman();
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

    @Override
    public int getSpeed() {
        return Config.ENEMY_SPEED;
    }


}
