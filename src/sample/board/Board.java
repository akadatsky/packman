package sample.board;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sample.model.*;

import java.util.Random;

public class Board {

    public static enum Side {TOP, RIGHT, LEFT, BOTTOM}

    public final Random rand = new Random();

    private Unit[][] field;

    public Board(GraphicsContext gc) {
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
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].draw();
            }
        }
    }

    public void move() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].move();
            }
        }
    }

    public void keyPressed(KeyCode code) {
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
        if (opponent instanceof Stone){
            return false;
        } else {
            return true;
        }
    }

}
