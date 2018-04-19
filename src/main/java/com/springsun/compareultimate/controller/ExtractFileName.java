package com.springsun.compareultimate.controller;

import javax.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Extracts file name from HTTP header content-disposition
 */
public class ExtractFileName {
    final static Logger logger = LogManager.getLogger(ExtractFileName.class);

    public static String getNameFromPart(Part part){
        part.getContentType();
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        logger.warn("No file name has been found in HTTP header");
        return "";
    }

}
