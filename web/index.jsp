<%--
  Created by IntelliJ IDEA.
  User: lakshan
  Date: 11/12/16
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>File Uploading Form</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action="UploadServlet" method="post"
      enctype="multipart/form-data">
  <input type="file" name="file" size="50" />
  <br />
  <input type="submit" value="Upload File" />
</form>
</body>
</html>
