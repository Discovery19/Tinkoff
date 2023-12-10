package edu.project4;

import lombok.Getter;

public class Pixel {
    //CHECKSTYLE:OFF: checkstyle:EmptyLineSeparator
    @Getter int counter;
    @Getter double red, green, blue, normal;

    public Pixel() {
        this.counter = 0;
        this.red = 0.0;
        this.green = 0.0;
        this.blue = 0.0;
        this.normal = 0.0;
    }

    public void increment() {
        counter++;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }
}
