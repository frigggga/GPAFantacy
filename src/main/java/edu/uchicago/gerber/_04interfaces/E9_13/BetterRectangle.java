package edu.uchicago.gerber._04interfaces.E9_13;

import java.awt.*;

public class BetterRectangle extends Rectangle {
    public BetterRectangle(int x, int y, int width, int height){
        super.setLocation(x, y);
        super.setSize(width, height);
    }

    public double getPerimeter(){
        return 2 * (this.getHeight() + this.getWidth());
    }

    public double getArea(){
        return this.getWidth() * this.getHeight() ;
    }

}
