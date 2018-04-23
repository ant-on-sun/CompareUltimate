package com.springsun.compareultimate.view;

import com.springsun.compareultimate.controller.CompareFiles;
import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.ResultOfComparing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/Comparing")
public class ResultServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(ResultServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String appDirectory = System.getProperty("user.home") + File.separator + "ImageComparing" + File.separator;
        CompareFiles.compareThem(appDirectory);
        logger.info("Files have been compared");

        request.setAttribute("filesToCompare", FilesToCompare.getInstance());
        request.setAttribute("resultOfComparing", ResultOfComparing.getInstance());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/Comparing.jsp");
        requestDispatcher.forward(request, response);
    }

}
