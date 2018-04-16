package com.springsun.compareultimate.model;

public class Rectangles {

    static {
        instance = new Rectangles();
    }

    private static Rectangles instance;
    private int[][] rectangles;

    public void setRectangles(int[][] rectangles) {
        this.rectangles = rectangles;
    }

    public int[][] getRectangles() {
        return rectangles;
    }

    public static Rectangles getInstance(){
        return instance;
    }
}
