package com.springsun.compareultimate.controller;

public class TrimFileName {

    public static String cutOffExcess(String fullName){
        String result = "";
        int i = fullName.lastIndexOf("\\");
        int j = fullName.lastIndexOf("/");
        if (i < 0 && j < 0) return fullName;
        if (i >= 0){
            result = fullName.substring(i+1);
        }
        if (j >= 0){
            result = fullName.substring(j+1);
        }
        return result;
    }
}
