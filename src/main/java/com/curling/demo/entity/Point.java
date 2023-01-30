package com.curling.demo.entity;

import java.text.DecimalFormat;
import java.util.Collection;

public class Point {
    public double x;
    public double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
