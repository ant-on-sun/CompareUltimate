package com.springsun.compareultimate.view;

import com.springsun.compareultimate.controller.CreateUniqueFileName;
import com.springsun.compareultimate.controller.ExtractFileName;
import com.springsun.compareultimate.controller.ProcessFormField;
import com.springsun.compareultimate.controller.ProcessUploadedFile;
import com.springsun.compareultimate.model.FilesToCompare;
import com.springsun.compareultimate.model.IteratorValue;
import com.springsun.compareultimate.model.ResultOfComparing;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/UploadFile",
            initParams = {
                @WebInitParam(name = "allowedTypes", value = "jpg,jpeg,png")
            })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2Mb (file’s size that is greater than this threshold
                                                      // will be directly written to disk, instead of saving in memory)
                 maxFileSize = 1024 * 1024 * 10,      // 10Mb (maximum size for a single upload file)
                 maxRequestSize = 1024 * 1024 * 30)   // 30Mb (maximum size for a request)
public class MainPageServlet extends HttpServlet {
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
        System.out.println("In MainPageServlet doGet()");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mainpage.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println("In MainPageServlet doPost() before .clear()");
        FilesToCompare.getInstance().clear();
        System.out.println("In MainPageServlet doPost() after .clear()");

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
                System.out.println(savePath + File.separator + fileName);
            }
        }

//        //Check if the request is multipart
//        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
//        if (!isMultiPart){
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//        //Creating class factory:
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        //Maximum size of data buffer (bites). If it will be exceeded, than data will be written to temporary directory.
//        factory.setSizeThreshold(1024 * 1024); // 1 Mb
//        //Temporary directory:
//        File tempDirectory = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(tempDirectory);
//        //Creating loader:
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        //Maximum size of loaded data (bites). Default value is -1 (without limits).
//        upload.setFileSizeMax(1024 * 1024 * 10); // 10 Mb

//        try {
//            List items = upload.parseRequest(request);
//            Iterator iterator = items.iterator();
//            while (iterator.hasNext()){
//                FileItem fileItem = (FileItem)iterator.next();
//                if (fileItem.isFormField()){ //if received part of data is a field of form
//                    ProcessFormField.handleFormField(fileItem);
//                } else { // Regard it as a part of a file
//                    ProcessUploadedFile.handleUploadedFile(this, fileItem);
//                }
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return;
//        }
        System.out.println("In MainPageServlet doPost() after uploading files");

        request.setAttribute("filesToCompare", FilesToCompare.getInstance());
        request.setAttribute("resultOfComparing", ResultOfComparing.getInstance());
        request.setAttribute("iteratorValue", IteratorValue.getInstance());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/Comparing.jsp");
        requestDispatcher.forward(request, response);

    }
}
