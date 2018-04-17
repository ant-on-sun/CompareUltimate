package com.springsun.compareultimate.view;

import com.springsun.compareultimate.controller.CompareFiles;
import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.ResultOfComparing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        CompareFiles.compareThem(request.getServletContext().getRealPath(""));

        request.setAttribute("filesToCompare", FilesToCompare.getInstance());
        request.setAttribute("resultOfComparing", ResultOfComparing.getInstance());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/Comparing.jsp");
        requestDispatcher.forward(request, response);
    }

}
