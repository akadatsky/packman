package sample.board;

import javafx.scene.canvas.GraphicsContext;
import sample.model.*;

public class Board {

    private Unit[][] field;

    public Board(GraphicsContext gc) {
        int lineCount = Map.getMap().size();
        field = new Unit[lineCount][];
        for (int i = 0; i < lineCount; i++) {
            int unitInLineCount = Map.getMap().get(i).length();
            field[i] = new Unit[unitInLineCount];
            for (int j = 0; j < unitInLineCount; j++) {
               field[i][j] = createUnit(gc, Map.getMap().get(i).charAt(j), i, j);
            }
        }
    }

    private Unit createUnit(GraphicsContext gc, char c, int i, int j) {
        switch (c){
            case 'a':
                return new Packman(gc, this, i, j);
            case '1':
                return new Stone(gc, this, i, j);
            case 'g':
                return new Gold(gc, this, i, j);
            case 'e':
                return new Enemy(gc, this, i, j);
            default:
                return new EmptyCell(gc, this, i, j);
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
}
