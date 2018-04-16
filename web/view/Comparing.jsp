<%@ page import="java.util.List" %>
<%@ page import="com.springsun.compareultimate.model.FilesToCompare"%>
<%@ page import="com.springsun.compareultimate.model.ResultOfComparing"%>
<%@ page import="com.springsun.compareultimate.model.IteratorValue" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Comparing</title>
        <link rel="stylesheet" href="styles/w3.css">
    </head>

    <body class="w3-light-grey">
        <!-- header -->
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Image Comparing</h1>
        </div>

        <div>
            <%FilesToCompare filesToCompare = (FilesToCompare)request.getAttribute("filesToCompare");
                IteratorValue iteratorValue = (IteratorValue)request.getAttribute("iteratorValue");
            if (filesToCompare.getPathToFileList() != null && !filesToCompare.getPathToFileList().isEmpty()){
                for (int i = 0; i < filesToCompare.getPathToFileList().size(); i++){
                    //iteratorValue.setIteratorValue(i);
                    System.out.println("in Comparing.jsp i = " + i);
                    out.println("<img width=\"300\" src=\"/images/" + i + "\" alt=File" + (i + 1) + ">");
                    System.out.println("In Comparing.jsp trying to load images to page. Load file " + (i + 1) +
                    " with path = " + filesToCompare.getPathToFileList().get(i));
                }
            }%>
        </div>

        <div>
            <button onclick="location.href='/Comparing'">Compare them</button>
        </div>

        <div>
            <%ResultOfComparing resultOfComparing = (ResultOfComparing)request.getAttribute("resultOfComparing");
            if (!resultOfComparing.getFileName().equals("")){
                out.println("<img width=\"300\" src=\"/images/result\" alt=Result>");
                System.out.println("In Comparing.jsp trying to show result image to page.");
            }

            %>
        </div>

        <div>
            <button onclick="location.href='/Main'">Back to main</button>
        </div>


    </body>
</html>