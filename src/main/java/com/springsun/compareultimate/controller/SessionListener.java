package com.springsun.compareultimate.controller;

import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.ResultOfComparing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    final static Logger logger = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent e) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        // Delete the files when session has been destroyed
        FilesToCompare filesToCompare = (FilesToCompare)e.getSession().getAttribute("filesToCompare");
        ResultOfComparing resultOfComparing = (ResultOfComparing)e.getSession().getAttribute("resultOfComparing");
        if (filesToCompare.getFileNameList() != null && !filesToCompare.getFileNameList().isEmpty()){
            filesToCompare.clear();
            logger.info("Files have been removed from disk when session has been destroyed");
        }
        if (resultOfComparing.getFileName() != ""){
            resultOfComparing.clear();
            logger.info("Result file has been removed from disk when session has been destroyed");
        }
    }

}
