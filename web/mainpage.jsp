<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Image Comparing</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>

    <body class="w3-light-gray">
        <!-- header -->
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Image Comparing</h1>
        </div>

        <div class="w3-container w3-padding">
            <h3>File Upload:</h3>
        </div>

        <div class="w3-container w3-green">
            Select files to upload: <br />
        </div>



        <form action = "MainPageServlet" method = "post"
            enctype = "multipart/form-data" class="w3-selection w3-light-grey w3-padding">
            Файл 1:<input type = "file" name = "fileOne" size = "50" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
            <br />
            Файл 2:<input type = "file" name = "fileTwo" size = "50" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
            <br />
            <input type="submit" name = "Upload files" class="w3-input w3-hover-aqua w3-green w3-round w3-margin-bottom"/>
        </form>
    </body>
</html>