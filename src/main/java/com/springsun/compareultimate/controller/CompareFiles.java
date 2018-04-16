package com.springsun.compareultimate.controller;

import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.Rectangles;
import com.springsun.compareultimate.model.ResultOfComparing;
import com.springsun.compareultimate.model.SetOfSets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CompareFiles {
    private static final String SAVE_DIR = "result";

    public static void compareThem(String appDirectory){
        String resultFileName = "result.png";
        String savePath = appDirectory + SAVE_DIR;
        int[] pixelOne = new int[4];
        int[] pixelTwo = new int[4];
        boolean pixelsAreDifferent;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        resultFileName = CreateUniqueFileName.getName(resultFileName, savePath);
        String pathToFileAsString = savePath + File.separator + resultFileName;
        FilesToCompare filesToCompare = FilesToCompare.getInstance();
        ResultOfComparing resultOfComparing = ResultOfComparing.getInstance();
        BufferedImage bufferedImageOne = null;
        BufferedImage bufferedImageTwo = null;
        try {
            bufferedImageOne = ImageIO.read(new File(filesToCompare.getPathToFileList().get(0)));
            bufferedImageTwo = ImageIO.read(new File(filesToCompare.getPathToFileList().get(1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        WritableRaster writableRasterOne = bufferedImageOne.getRaster();
        WritableRaster writableRasterTwo = bufferedImageTwo.getRaster();

        //Comparing logic here
        int resultHeight = bufferedImageOne.getHeight();
        int resultWidth = bufferedImageOne.getWidth();
        HandlePixels handlePixels = new HandlePixels(new int[resultHeight][resultWidth], resultHeight, resultWidth);
        for (int y = 0; y < resultHeight; y++){
            if (y >= bufferedImageTwo.getHeight()) {
                continue;
            }
            for (int x = 0; x < resultWidth; x++){
                if (x >= bufferedImageTwo.getWidth()){
                    continue;
                }
                pixelsAreDifferent = ComparePixels.areTheyDifferent(writableRasterOne.getPixel(x, y, pixelOne),
                        writableRasterTwo.getPixel(x, y, pixelTwo));
                if (pixelsAreDifferent){
                    handlePixels.markDifferentPixels(y, x);
                }
            }
        }

        //Processing logic here
        handlePixels.processDifference();

        //Prepare result image (write rectangles)
        Rectangles rectangles = Rectangles.getInstance();
        int[][] rectanglesResult = rectangles.getRectangles();
        for (int y = 0; y < resultHeight; y++){
            for (int x = 0; x < resultWidth; x++){
                if (rectanglesResult[y][x] == -1){
                    writableRasterOne.setPixel(x, y, new int[]{255, 0, 0, 0});
                }
            }
        }

        //Save image as PNG file:
        File resultFile = new File(pathToFileAsString);
        try {
            ImageIO.write(bufferedImageOne, "png", resultFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultOfComparing.setFileName(resultFileName).setPathToFile(pathToFileAsString);
    }
}
