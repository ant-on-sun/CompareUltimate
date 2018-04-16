package com.springsun.compareultimate.controller;

import javax.servlet.http.Part;

/**
 * Extracts file name from HTTP header content-disposition
 */
public class ExtractFileName {

    public static String getNameFromPart(Part part){
        part.getContentType();
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
