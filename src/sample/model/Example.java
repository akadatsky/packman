package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Example {

    private static final double SIZE = 30;
    private static final double SHIFT = 5;
    private static final int MAX_SPEED = 5;

    private GraphicsContext gc;
    private Example[] list;
    private double x = 0;
    private double y = 0;
    private double width = SIZE;
    private double height = SIZE;
    private double shiftX = SHIFT;
    private double shiftY = SHIFT;
    private boolean visible = true;
    private Random rand = new Random();

    public Example(GraphicsContext gc, Example[] list, double x, double y) {
        this.gc = gc;
        this.list = list;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        if (!visible) {
            return;
        }
        gc.setFill(Color.GAINSBORO);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(0.5);
        gc.fillOval(x, y, width, height);
        gc.strokeOval(x, y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private double getDistance(Example c) {
        return Math.sqrt(Math.pow(this.getX() - c.getX(), 2) +
                Math.pow(this.getY() - c.getY(), 2));
    }

    public void move() {
        if (!this.visible) {
            return;
        }
        x += shiftX;
        y += shiftY;
        addFormBordersReflection();

        // comment this to see difference
        addClashLogic();
    }

    private void addFormBordersReflection() {
        if (x > gc.getCanvas().getWidth() - width) {
            x = gc.getCanvas().getWidth() - width;
            shiftX = -(rand.nextInt(MAX_SPEED) + 1);
        }
        if (x < 0) {
            shiftX = rand.nextInt(MAX_SPEED) + 1;
        }
        if (y > gc.getCanvas().getHeight() - height) {
            y = gc.getCanvas().getHeight() - height;
            shiftY = -(rand.nextInt(MAX_SPEED) + 1);
        }
        if (y < 0) {
            shiftY = rand.nextInt(MAX_SPEED) + 1;
        }
    }

    private void addClashLogic() {
        for (Example c : list) {
            if (c != this && c.visible && getDistance(c) < (this.width / 2.0 + c.width / 2.0)) {
                if (getX() < c.getX()) {
                    shiftX = -(rand.nextInt(MAX_SPEED) + 1);
                } else {
                    shiftX = rand.nextInt(MAX_SPEED) + 1;
                }
                if (getY() < c.getY()) {
                    shiftY = -(rand.nextInt(MAX_SPEED) + 1);
                } else {
                    shiftY = rand.nextInt(MAX_SPEED) + 1;
                }

                // comment this to see difference
                maybeEatOnClash(c);

                break;
            }
        }
    }

    private void maybeEatOnClash(Example c) {
        if (this.width > c.width) {
            c.visible = false;
        } else {
            this.visible = false;
        }
        this.width += c.width / 10;
        this.height += c.height / 10;
        c.width += this.width / 10;
        c.height += this.height / 10;
    }

}
