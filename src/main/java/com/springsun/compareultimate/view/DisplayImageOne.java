package com.springsun.compareultimate.view;

import com.springsun.compareultimate.model.FilesToCompare;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/images/0")
public class DisplayImageOne extends HttpServlet {
    final static Logger logger = LogManager.getLogger(DisplayImageOne.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        FilesToCompare filesToCompare = FilesToCompare.getInstance();
        //as far as I know, this (below) works for PNG as well.
        // You might want to change the mapping to /images/*.jpg if it's giving problems
        response.setContentType("image/jpeg");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(filesToCompare.getPathToFileList().get(0));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(servletOutputStream);
        int ch = 0;
        while ((ch = bufferedInputStream.read()) != -1){
            bufferedOutputStream.write(ch);
        }
        bufferedInputStream.close();
        fileInputStream.close();
        bufferedOutputStream.close();
        servletOutputStream.close();
        logger.info("Image one has been displayed");
    }
}
