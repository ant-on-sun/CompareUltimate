package com.springsun.compareultimate.view;

import com.springsun.compareultimate.controller.CreateUniqueFileName;
import com.springsun.compareultimate.controller.ExtractFileName;
import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.ResultOfComparing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = {"/UploadFile", "/"},
            initParams = {
                @WebInitParam(name = "allowedTypes", value = "jpg,jpeg,png")
            })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2Mb (file’s size that is greater than this threshold
                                                      // will be directly written to disk, instead of saving in memory)
                 maxFileSize = 1024 * 1024 * 10,      // 10Mb (maximum size for a single upload file)
                 maxRequestSize = 1024 * 1024 * 30)   // 30Mb (maximum size for a request)
public class MainPageServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(MainPageServlet.class);

    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "upload";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        logger.trace("In MainPageServlet doGet()");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mainpage.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        FilesToCompare.getInstance().clear();

        // gets absolute path of the web application:
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + SAVE_DIR;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            String fileName = ExtractFileName.getNameFromPart(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")){
                //Avoiding name conflict
                fileName = CreateUniqueFileName.getName(fileName, savePath);
                //Writing file to disk
                part.write(savePath + File.separator + fileName);
                //Filling model
                FilesToCompare.getInstance().addPath(savePath + File.separator + fileName).addNewFileName(fileName);
            }
        }
        logger.info("Files have been loaded");
        request.setAttribute("filesToCompare", FilesToCompare.getInstance());
        request.setAttribute("resultOfComparing", ResultOfComparing.getInstance());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/Comparing.jsp");
        requestDispatcher.forward(request, response);

    }
}
