package com.springsun.compareultimate.controller;

import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.view.MainPageServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Random;

public class ProcessUploadedFile {
    private static Random random = new Random();

    public static void handleUploadedFile(MainPageServlet mainPageServlet, String fileName) throws Exception{
        File uploadedFile = null;
        String pathAsString;
        String newFileName;
        //Choosing filename until get unoccupied (free) name:
        do {
            System.out.println("Name of file: " + fileName);
            //newFileName = TrimFileName.cutOffExcess(fileItem.getName());
            newFileName = random.nextInt(Integer.MAX_VALUE) + fileName;
            pathAsString = mainPageServlet.getServletContext().getRealPath(File.separator + "upload"
                    + File.separator + newFileName);
            uploadedFile = new File(pathAsString);
        } while (uploadedFile.exists());
        //Creating file:
        //uploadedFile.createNewFile();
        FileUtils.touch(uploadedFile);
        //Writing data to the created file:
        //fileItem.write(uploadedFile);
        FilesToCompare.getInstance().addPath(pathAsString).addNewFileName(newFileName);
    }
}
