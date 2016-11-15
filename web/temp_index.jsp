<%-- 
    Document   : index
    Created on : 12-Nov-2016, 18:14:11
    Author     : ndine
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "egodage100";

        out.println("Connecting database...");
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement( );
            String SQL = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery( SQL );
            out.println("Database connected!");
            rs.next( );
            String userName = rs.getString("userName");
            String passWord = rs.getString("passWord");
            out.println("<br>");
            out.println("UserName: "+userName+"\nPassWord: "+passWord);
        } catch (SQLException e) {
            //throw new IllegalStateException
            out.println("Cannot connect the database!");
            out.println(e.getMessage());
        }
        %>
        <h1>Hello World!</h1>
    </body>
</html>
