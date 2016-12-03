package sample.board;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Config;
import sample.model.EmptyCell;
import sample.model.Enemy;
import sample.model.Gold;
import sample.model.Pacman;
import sample.model.Stone;
import sample.model.Unit;

import java.util.List;
import java.util.Random;

public class Board {

    private final int lineCount;
    private final int columnCount;

    public enum Side {TOP, RIGHT, LEFT, BOTTOM}

    public final Random rand = new Random();

    private final Unit[][] field;
    private int goldCount = 0;

    private Pacman pacman;
    private boolean finished = false;

    private final GraphicsContext gc;

    public Board(GraphicsContext gc) {
        this.gc = gc;
        List<String> map = Map.getMap();
        lineCount = map.size();
        columnCount = map.get(0).length();
        field = new Unit[lineCount][];
        for (int i = 0; i < lineCount; i++) {
            field[i] = new Unit[columnCount];
            for (int j = 0; j < columnCount; j++) {
                field[i][j] = createUnit(gc, map.get(i).charAt(j), j, i);
            }
        }
    }

    private Unit createUnit(GraphicsContext gc, char c, int x, int y) {
        switch (c) {
            case 'a':
                return new Pacman(gc, this, x, y);
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

        for (Unit[] line : field) {
            for (Unit unit : line) {
                unit.draw();
            }
        }

        if (finished && goldCount != 0) {
            gc.setFill(Color.RED);
            double fontSize = Config.CELL_SIZE;
            gc.setFont(new Font(fontSize));
            gc.fillText("Game Over", gc.getCanvas().getWidth() / 3, gc.getCanvas().getHeight() / 3);
        }
        if (finished && goldCount == 0) {
            gc.setFill(Color.GREEN);
            double fontSize = Config.CELL_SIZE;
            gc.setFont(new Font(fontSize));
            gc.fillText("You win!!!", gc.getCanvas().getWidth() / 3, gc.getCanvas().getHeight() / 3);
        }
    }

    public void move() {
        if (finished) {
            return;
        }
        for (Unit[] line : field) {
            for (Unit unit : line) {
                unit.move();
            }
        }
    }

    public void keyPressed(KeyCode code) {
        if (finished) {
            return;
        }
        for (Unit[] line : field) {
            for (Unit unit : line) {
                unit.keyPressed(code);
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
        return !(opponent instanceof Stone);
    }

    public void incGold() {
        goldCount++;
    }

    public void decGold() {
        goldCount--;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public boolean isTouchToPacman(Unit unit) {
        double distance = Math.sqrt(Math.pow(unit.getXCenter() - pacman.getXCenter(), 2) + Math.pow(unit.getYCenter() - pacman.getYCenter(), 2));
        return distance < unit.getRadius() + pacman.getRadius();
    }

    public void setGameOver() {
        finished = true;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
