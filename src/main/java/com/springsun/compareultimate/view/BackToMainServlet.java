package com.springsun.compareultimate.view;

import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.ResultOfComparing;
import com.springsun.compareultimate.model.SetOfSets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BackToMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println("In BackToMainServlet doGet()");
        FilesToCompare filesToCompare = FilesToCompare.getInstance();
        if (filesToCompare.getFileNameList() != null && !filesToCompare.getFileNameList().isEmpty()){
            filesToCompare.clear();
        }
        ResultOfComparing resultOfComparing = ResultOfComparing.getInstance();
        if (resultOfComparing.getFileName() != ""){
            resultOfComparing.clear();
        }
        SetOfSets setOfSets = SetOfSets.getInstance();
        if (!setOfSets.getSetOfSetsList().isEmpty()){
            setOfSets.clear();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mainpage.jsp");
        requestDispatcher.forward(request, response);
    }

}
