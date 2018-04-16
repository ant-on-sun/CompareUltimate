package com.springsun.compareultimate.controller;

import com.springsun.compareultimate.model.PixelYX;
import com.springsun.compareultimate.model.Rectangles;
import com.springsun.compareultimate.model.SetOfPixels;
import com.springsun.compareultimate.model.SetOfSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public class HandlePixels {

    private int[][] pixelsOfResult;
    private int height;
    private int width;
    private int marginOfPixelSet = 2;
    private SetOfSets setOfSets = SetOfSets.getInstance();
    private List<SetOfPixels> setOfSetsList = setOfSets.getSetOfSetsList();
    private Rectangles rectangles = Rectangles.getInstance();

    public HandlePixels(int[][] pixelsOfResult, int height, int width){
        this.pixelsOfResult = pixelsOfResult;
        this.height = height;
        this.width = width;
    }

    public void markDifferentPixels(int y, int x){
        pixelsOfResult[y][x] = -1;
    }

    public void processDifference(){
        spreadingAreasOfPixels();
        createSetsOfPixels();
        processMinMaxValuesInSets();
        mergeNeighbors();
        getRectangles();
    }

    private void spreadingAreasOfPixels(){
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if (pixelsOfResult[y][x] == -1){
                    //handle boundaries
                    if (x == 0){
                        if (pixelsOfResult[y][x + 1] == 0)
                        pixelsOfResult[y][x + 1] = -2;
                    }
                    if (x == width -1){
                        if (pixelsOfResult[y][x - 1] == 0)
                        pixelsOfResult[y][x - 1] = -2;
                    }
                    if (y == 0){
                        if (pixelsOfResult[y + 1][x] == 0)
                        pixelsOfResult[y + 1][x] = -2;
                    }
                    if (y == height - 1){
                        if (pixelsOfResult[y - 1][x] == 0)
                        pixelsOfResult[y - 1][x] = -2;
                    }

                    //handle other cases
                    if (x > 0 && x < width - 1){
                        if (pixelsOfResult[y][x - 1] == 0)
                        pixelsOfResult[y][x - 1] = -2;
                        if (pixelsOfResult[y][x + 1] == 0)
                        pixelsOfResult[y][x + 1] = -2;
                    }
                    if (y > 0 && y < height - 1){
                        if (pixelsOfResult[y - 1][x] == 0)
                        pixelsOfResult[y - 1][x] = -2;
                        if (pixelsOfResult[y + 1][x] == 0)
                        pixelsOfResult[y + 1][x] = -2;
                    }
//                    if ((x > 0 && x < width - 1) && (y > 0 && y < height - 1)){
//                        pixelsOfResult[y - 1][x - 1] = -2;
//                        pixelsOfResult[y - 1][x + 1] = -2;
//                        pixelsOfResult[y + 1][x - 1] = -2;
//                        pixelsOfResult[y + 1][x + 1] = -2;
//                    }

                }
            }
        }
    }

    private void createSetsOfPixels(){
        boolean needToBeAddToCurrentSet = false;
        List<Integer> indexOfSetList = new ArrayList<>();
        int index = -1;
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if (pixelsOfResult[y][x] < 0){
                    PixelYX pixelYX = new PixelYX(y, x);
                    if (!setOfSetsList.isEmpty()){

                        for (int i = 0; i < setOfSetsList.size(); i++){
                            SetOfPixels setOfPixels = setOfSetsList.get(i);
                            List<PixelYX> setOfPixelsList = setOfPixels.getSetOfPixelsList();
                            for (int j = 0; j < setOfPixelsList.size(); j++){
                                PixelYX p = setOfPixelsList.get(j);
                                if ((abs(y - p.getY()) < marginOfPixelSet) && (abs(x - p.getX()) < marginOfPixelSet)){
                                    needToBeAddToCurrentSet = true;
                                    indexOfSetList.add(i);
                                    index = i;
                                    break;
                                }
                            }
                        }
                        if (needToBeAddToCurrentSet){
                            setOfSetsList.get(index).addPixel(pixelYX);
                        } else {
                            SetOfPixels sPixels = new SetOfPixels();
                            sPixels.addPixel(pixelYX);
                            setOfSets.addSetOfPixels(sPixels);
                        }
                        needToBeAddToCurrentSet = false;
                        indexOfSetList.clear();
                    } else { //Create first set
                        SetOfPixels setOfPixels = new SetOfPixels();
                        setOfPixels.addPixel(pixelYX);
                        setOfSets.addSetOfPixels(setOfPixels);
                    }
                }
            }
        }
    }

    private void processMinMaxValuesInSets(){
        for (SetOfPixels setOfPixels : setOfSetsList){
            sortYX(setOfPixels);
        }
    }

    private void sortYX(SetOfPixels setOfPixels){
        int length = setOfPixels.getSetOfPixelsList().size();
        //Creating arrays of Y and X, than sort them to find min and max values
        int[] arrayY = new int[length];
        int[] arrayX = new int[length];
        int i = 0;
        for (PixelYX p : setOfPixels.getSetOfPixelsList()){
            arrayY[i] = p.getY();
            arrayX[i] = p.getX();
            i++;
        }
        System.out.println("arrayY = " + Arrays.toString(arrayY));
        System.out.println("arrayX = " + Arrays.toString(arrayX) + "\n");
        Arrays.sort(arrayY);
        Arrays.sort(arrayX);
        setOfPixels.setMinY(arrayY[0]);
        setOfPixels.setMinX(arrayX[0]);
        setOfPixels.setMaxY(arrayY[length - 1]);
        setOfPixels.setMaxX(arrayX[length - 1]);

    }

    private void getRectangles(){
        rectangles.setRectangles(new int[height][width]);
        int[][] resultRectangles = rectangles.getRectangles();
        for (SetOfPixels setOfPixels : setOfSetsList){
            int minY = setOfPixels.getMinY();
            int minX = setOfPixels.getMinX();
            int maxY = setOfPixels.getMaxY();
            int maxX = setOfPixels.getMaxX();
            System.out.println("minY = " + minY + " minX = " + minX + "\nmaxY = " + maxY + "maxX = " + maxX + "\n");

            for (int x = minX; x <= maxX; x++){
                resultRectangles[minY][x] = -1;
                resultRectangles[maxY][x] = -1;
            }
            for (int y = minY; y <= maxY; y++){
                resultRectangles[y][minX] = -1;
                resultRectangles[y][maxX] = -1;
            }
        }
    }

    private void mergeNeighbors(){
        List<Integer> setsToRemove = new ArrayList<>();
        for (int i = 0; i < setOfSetsList.size(); i++){
            SetOfPixels setOfPixels = setOfSetsList.get(i);
            int currentMinY = setOfPixels.getMinY();
            int currentMinX = setOfPixels.getMinX();
            int currentMaxY = setOfPixels.getMaxY();
            int currentMaxX = setOfPixels.getMaxX();
            for (int j = 1; j < setOfSetsList.size(); j++){
                SetOfPixels s = setOfSetsList.get(j);
                int minY = s.getMinY();
                int minX = s.getMinX();
                int maxY = s.getMaxY();
                int maxX = s.getMaxX();
                if (       (currentMinY >= minY && currentMinY <= maxY) && (currentMinX >= minX && currentMinX <= maxX)
                        || (currentMaxY >= minY && currentMaxY <= maxY) && (currentMaxX >= minX && currentMaxX <= maxX)
                        || (currentMaxY >= minY && currentMaxY <= maxY) && (currentMinX >= minX && currentMinX <= maxX)
                        || (currentMinY >= minY && currentMinY <= maxY) && (currentMaxX >= minX && currentMaxX <= maxX)
                        || (currentMinY <= minY && currentMaxY >= maxY) && (currentMinX >= minX && currentMinX <= maxX)
                        || (currentMinY <= minY && currentMaxY >= maxY) && (currentMaxX >= minX && currentMaxX <= maxX)
                        || (currentMinX <= minX && currentMaxX >= maxX) && (currentMinY >= minY && currentMinY <= maxY)
                        || (currentMinX <= minX && currentMaxX >= maxX) && (currentMaxY >= minY && currentMaxY <= maxY))

                {
                    setOfPixels.setMinY(min(currentMinY, minY));
                    s.setMinY(min(currentMinY, minY));
                    setOfPixels.setMinX(min(currentMinX, minX));
                    s.setMinX(min(currentMinX, minX));
                    setOfPixels.setMaxY(max(currentMaxY, maxY));
                    s.setMaxY(max(currentMaxY, maxY));
                    setOfPixels.setMaxX(max(currentMaxX, maxX));
                    s.setMaxX(max(currentMaxX, maxX));
                    setsToRemove.add(j);
                }
            }
        }
        for (int i = 0; i < setsToRemove.size(); i++){
//            setOfSetsList.remove(setsToRemove.get(i));
//            System.out.println("SetOfPixels with index = " + setsToRemove.get(i) + " has been removed");
        }
    }
}
