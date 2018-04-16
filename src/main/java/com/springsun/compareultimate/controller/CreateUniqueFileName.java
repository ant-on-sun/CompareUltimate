package com.springsun.compareultimate.controller;

import java.io.File;
import java.util.Random;

public class CreateUniqueFileName {
    private static Random random = new Random();

    public static String getName(String fileName, String savePath){
        File uploadedFile = null;
        String pathAsString;
        String newFileName;
        //Choosing filename until get unoccupied (free) name:
        do {
            newFileName = random.nextInt(Integer.MAX_VALUE) + fileName;
            pathAsString = savePath + File.separator + newFileName;
            uploadedFile = new File(pathAsString);
        } while (uploadedFile.exists());
        return newFileName;
    }
}
