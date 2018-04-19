package com.springsun.compareultimate.view;

import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.ResultOfComparing;
import com.springsun.compareultimate.model.SetOfSets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/Main")
public class BackToMainServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(BackToMainServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        FilesToCompare filesToCompare = FilesToCompare.getInstance();
        if (filesToCompare.getFileNameList() != null && !filesToCompare.getFileNameList().isEmpty()){
            filesToCompare.clear();
            logger.info("Files have been removed from disk");
        }
        ResultOfComparing resultOfComparing = ResultOfComparing.getInstance();
        if (resultOfComparing.getFileName() != ""){
            resultOfComparing.clear();
            logger.info("Result file has been removed from disk");
        }
        SetOfSets setOfSets = SetOfSets.getInstance();
        if (!setOfSets.getSetOfSetsList().isEmpty()){
            setOfSets.clear();
            logger.info("Set of sets has been cleared");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mainpage.jsp");
        requestDispatcher.forward(request, response);
    }

}
