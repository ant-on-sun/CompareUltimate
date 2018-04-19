package com.springsun.compareultimate.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Rectangles {
    final static Logger logger = LogManager.getLogger(Rectangles.class);

    static {
        instance = new Rectangles();
    }

    private static Rectangles instance;
    private int[][] rectangles;

    public void setRectangles(int[][] rectangles) {
        this.rectangles = rectangles;
        logger.info("Rectangles have been set");
    }

    public int[][] getRectangles() {
        return rectangles;
    }

    public static Rectangles getInstance(){
        return instance;
    }
}
