package io.codeforall.ironMaven;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class RectagleObject extends GameObjects {

    private Rectangle rectangle;
    int lastMove;
    private boolean isPainted;


    public RectagleObject(double initX, double initY, double width, double height) {
        super();
        this.rectangle = new Rectangle(initX, initY, width, height);
        double right = rectangle.getX() + rectangle.getWidth();
        double bottom = rectangle.getY() + rectangle.getHeight();
        double left = rectangle.getX();
        double up = rectangle.getY();
        setObjectBounds(right, up, left, bottom);
        this.setColor(Color.BLACK);
        lastMove = 2;

    }

    public boolean isPainted() {
        return isPainted;
    }

    public void setPainted(boolean painted) {
        isPainted = painted;
    }

    public void fill() {
        this.rectangle.fill();
    }

    public void draw() {
        this.rectangle.draw();
    }

    public void setColor(Color color) {
        this.rectangle.setColor(color);
    }


    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void translate(int x, int y) {
        this.rectangle.translate(x, y);
        double right = rectangle.getX() + rectangle.getWidth();
        double bottom = rectangle.getY() + rectangle.getHeight();
        double left = rectangle.getX();
        double up = rectangle.getY();
        setObjectBounds(right, up, left, bottom);

    }

}
