package com.springsun.compareultimate.controller;

import static java.lang.Math.abs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComparePixels {
    final static Logger logger = LogManager.getLogger(ComparePixels.class);
    private static int differenceValue = 25;

    public static boolean areTheyDifferent (int[] pixelOne, int[] pixelTwo){

        for (int i = 0; i < pixelOne.length; i++){
            if (pixelOne[i] == pixelTwo[i]){
                continue;
            }
            if (abs(pixelOne[i] - pixelTwo[i]) >= differenceValue){
                logger.trace("in ComparePixels.areTheyDifferent() different pixels have been found");
                return true;
            }
        }
        return false;
    }
}
