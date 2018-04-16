package com.springsun.compareultimate.controller;

import static java.lang.Math.abs;

public class ComparePixels {
    private static int differenceValue = 25;

    public static boolean areTheyDifferent (int[] pixelOne, int[] pixelTwo){

        for (int i = 0; i < pixelOne.length; i++){
            if (pixelOne[i] == pixelTwo[i]){
                continue;
            }
            if (abs(pixelOne[i] - pixelTwo[i]) >= differenceValue){
                return true;
            }
        }
        return false;
    }
}
