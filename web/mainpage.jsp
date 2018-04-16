<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Image Comparing</title>
        <link rel="stylesheet" href="styles/w3.css">
    </head>

    <body class="w3-light-grey">
        <!-- header -->
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Image Comparing</h1>
        </div>

        <h3>File Upload:</h3>
        Select files to upload: <br />
        <form action = "MainPageServlet" method = "post"
            enctype = "multipart/form-data">
            Файл 1:<input type = "file" name = "fileOne" size = "50" />
            <br />
            Файл 2:<input type = "file" name = "fileTwo" size = "50" />
            <br />
            <input type="submit" name = "Upload files" />
        </form>
    </body>
</html>