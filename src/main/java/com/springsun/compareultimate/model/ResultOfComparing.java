package com.springsun.compareultimate.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultOfComparing {
    final static Logger logger = LogManager.getLogger(ResultOfComparing.class);

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
            logger.warn("Can't delete result file in path: " + path.toString() + "\nIOException: ", e);
            //e.printStackTrace();
        }
    }
}
