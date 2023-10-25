package edu.hw2.Task2;

public class Rectangle {
    private int width;
    private int height;

    public final void setWidth(int w) {
        this.width = w;
    }

    public final void setHeight(int h) {
        this.height = h;
    }

    public int getArea() {
        return width * height;
    }
}
