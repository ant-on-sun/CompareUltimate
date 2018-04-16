package com.springsun.compareultimate.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResultOfComparing {

    static {
        instance = new ResultOfComparing();
    }

    private static ResultOfComparing instance;
    private String pathToFile;
    private String fileName;

    private ResultOfComparing(){
        pathToFile = "";
        fileName = "";
    }

    public static ResultOfComparing getInstance(){
        return instance;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public String getFileName() {
        return fileName;
    }

    public ResultOfComparing setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
        return this;
    }

    public ResultOfComparing setFileName(String fileName){
        this.fileName = fileName;
        return this;
    }

    public void clear(){
        deleteFile();
        pathToFile = "";
        fileName = "";
    }

    private void deleteFile(){
        Path path = Paths.get(pathToFile);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
