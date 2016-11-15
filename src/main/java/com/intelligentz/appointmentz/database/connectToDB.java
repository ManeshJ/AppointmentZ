package com.intelligentz.appointmentz.database;


import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ndine
 */

final public class connectToDB {
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;
    
    public connectToDB() throws ClassNotFoundException{
        url = "jdbc:mysql://localhost:3306/appointmentz";
        username = "root";
        password = "luckygrox";
        Class.forName("com.mysql.jdbc.Driver");
    }
    public boolean connect(){
        try {
             connection = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            //throw new IllegalStateException
            System.out.println("Cannot connect the database!");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void showErrormessage(HttpServletResponse res) throws IOException{
        PrintWriter pw=res.getWriter();//get the stream to write the data
        //writing html in the stream
        pw.println("<html><body>");
        pw.println("Database connection failed");
        pw.println("</body></html>");

        pw.close();//closing the stream
    }
    
}
