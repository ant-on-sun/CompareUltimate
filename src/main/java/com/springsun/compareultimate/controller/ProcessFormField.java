package com.springsun.compareultimate.controller;

import org.apache.commons.fileupload.FileItem;

public class ProcessFormField {
    public static void handleFormField(FileItem fileItem){
        System.out.println(fileItem.getFieldName() + " = " + fileItem.getString());
    }
}
