<%-- 
    Document   : error
    Created on : 14-Nov-2016, 08:51:35
    Author     : ndine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h2>Error occurred : <%=request.getParameter("error")%> </h2>
    </body>
</html>
