<%@ page import="com.springsun.compareultimate.model.FilesToCompare"%>
<%@ page import="com.springsun.compareultimate.model.ResultOfComparing"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Comparing</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>

    <body class="w3-light-grey">
        <!-- header -->
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Image Comparing</h1>
        </div>

        <div class="w3-container w3-grey w3-opacity w3-center w3-padding">
            <%FilesToCompare filesToCompare = (FilesToCompare)request.getAttribute("filesToCompare");
            if (filesToCompare.getPathToFileList() != null && !filesToCompare.getPathToFileList().isEmpty()){
                for (int i = 0; i < filesToCompare.getPathToFileList().size(); i++){
                    out.println("<img width=\"300\" src=\"/images/" + i + "\" alt=File" + (i + 1) + ">");
                }
            }%>
        </div>

        <div class="w3-container w3-light-blue w3-opacity w3-center w3-padding">
            <button class="w3-btn w3-hover-aqua w3-round" onclick="location.href='/Comparing'">Compare them</button>
        </div>

        <div class="w3-container w3-center">
            <%ResultOfComparing resultOfComparing = (ResultOfComparing)request.getAttribute("resultOfComparing");
            if (!resultOfComparing.getFileName().equals("")){
                out.println("<img width=\"300\" src=\"/images/result\" alt=Result>");
            }%>
        </div>

        <div class="w3-container w3-light-blue w3-opacity w3-left-align w3-padding">
            <button class="w3-btn w3-hover-amber w3-round" onclick="location.href='/Main'">Back to main</button>
        </div>

    </body>
</html>