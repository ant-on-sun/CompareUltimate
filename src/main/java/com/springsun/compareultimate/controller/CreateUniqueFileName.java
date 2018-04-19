package com.springsun.compareultimate.controller;

import java.io.File;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateUniqueFileName {
    final static Logger logger = LogManager.getLogger(CreateUniqueFileName.class);
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
        logger.trace("in CreateUniqueFileName.getName()");
        return newFileName;
    }
}
