package com.springsun.compareultimate.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilesToCompare {
    final static Logger logger = LogManager.getLogger(FilesToCompare.class);

    static {
        instance = new FilesToCompare();
    }

    private static FilesToCompare instance;
    private List<String> pathToFileList;
    private List<String> fileNameList;

    private FilesToCompare(){
        pathToFileList = new ArrayList<>();
        fileNameList = new ArrayList<>();
    }

    public static FilesToCompare getInstance(){
        return instance;
    }

    public FilesToCompare addPath(String path){
        pathToFileList.add(path);
        return this;
    }

    public FilesToCompare addNewFileName(String newName){
        fileNameList.add(newName);
        return this;
    }

    public List<String> getPathToFileList(){
        return pathToFileList;
    }

    public List<String> getFileNameList(){
        return fileNameList;
    }

    public void clear(){
        deleteFiles();
        pathToFileList.clear();
        fileNameList.clear();
    }

    private void deleteFiles(){
        if (pathToFileList != null && !pathToFileList.isEmpty()){
            for (int i = 0; i < pathToFileList.size(); i++){
                Path path = Paths.get(pathToFileList.get(i));
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    logger.warn("Can't delete file at path: " + path.toString() + " \nIOException: ", e);
                    //e.printStackTrace();
                }
            }
        }
    }

}
