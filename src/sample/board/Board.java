package sample.board;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.model.*;

import java.util.Random;

public class Board {

    public static enum Side {TOP, RIGHT, LEFT, BOTTOM}

    public final Random rand = new Random();

    private Unit[][] field;
    private int goldCount = 0;

    private Packman packman;
    private boolean finished = false;

    private GraphicsContext gc;

    public Board(GraphicsContext gc) {
        this.gc = gc;
        int lineCount = Map.getMap().size();
        field = new Unit[lineCount][];
        for (int i = 0; i < lineCount; i++) {
            int unitInLineCount = Map.getMap().get(i).length();
            field[i] = new Unit[unitInLineCount];
            for (int j = 0; j < unitInLineCount; j++) {
                field[i][j] = createUnit(gc, Map.getMap().get(i).charAt(j), j, i);
            }
        }
    }

    private Unit createUnit(GraphicsContext gc, char c, int x, int y) {
        switch (c) {
            case 'a':
                return new Packman(gc, this, x, y);
            case '1':
                return new Stone(gc, this, x, y);
            case 'g':
                return new Gold(gc, this, x, y);
            case 'e':
                return new Enemy(gc, this, x, y);
            default:
                return new EmptyCell(gc, this, x, y);
        }
    }

    public void draw() {

        if (goldCount == 0) {
            finished = true;
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].draw();
            }
        }

        if (finished && goldCount != 0) {
            gc.setFill(Color.RED);
            double fontSize = 80.0;
            gc.setFont(new Font(fontSize));
            gc.fillText("Game Over", 150, 280);
        }
        if (finished && goldCount == 0) {
            gc.setFill(Color.GREEN);
            double fontSize = 80.0;
            gc.setFont(new Font(fontSize));
            gc.fillText("You win!!!", 150, 280);
        }
    }

    public void move() {
        if (finished) {
            return;
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].move();
            }
        }
    }

    public void keyPressed(KeyCode code) {
        if (finished) {
            return;
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].keyPressed(code);
            }
        }
    }


    public boolean canMove(Unit unit, Side side) {
        int x = unit.getXCell();
        int y = unit.getYCell();
        Unit opponent = null;
        switch (side) {
            case TOP:
                opponent = field[y - 1][x];
                break;
            case RIGHT:
                opponent = field[y][x + 1];
                break;
            case BOTTOM:
                opponent = field[y + 1][x];
                break;
            case LEFT:
                opponent = field[y][x - 1];
                break;
        }
        if (opponent instanceof Stone) {
            return false;
        } else {
            return true;
        }
    }

    public void incGold() {
        goldCount++;
    }

    public void decGold() {
        goldCount--;
    }

    public void setPackman(Packman packman) {
        this.packman = packman;
    }

    public boolean isTouchToPackman(Unit unit) {
        double distance = Math.sqrt(Math.pow(unit.getXCenter() - packman.getXCenter(), 2) + Math.pow(unit.getYCenter() - packman.getYCenter(), 2));
        if (distance < unit.getRadius() + packman.getRadius()) {
            return true;
        } else {
            return false;
        }
    }

    public void setGameOver() {
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }
}
