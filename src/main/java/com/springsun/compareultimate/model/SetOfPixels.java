package com.springsun.compareultimate.model;

import java.util.ArrayList;
import java.util.List;

public class SetOfPixels {

    private List<PixelYX> setOfPixelsList;
    private int minY;
    private int minX;
    private int maxY;
    private int maxX;

    public int getMinY() {
        return minY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }



    public SetOfPixels(){
        setOfPixelsList = new ArrayList<>();
    }

    public void addPixel(PixelYX pixelYX){
        setOfPixelsList.add(pixelYX);
    }

//    public PixelYX getPixelYX(int i){
//        return setOfPixelsList.get(i);
//    }

    public List<PixelYX> getSetOfPixelsList() {
        return setOfPixelsList;
    }
}
