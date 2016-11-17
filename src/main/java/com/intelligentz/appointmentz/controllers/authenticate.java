/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelligentz.appointmentz.controllers;
import com.intelligentz.appointmentz.database.connectToDB;
import com.mysql.jdbc.Connection;
import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ndine
 */
public class authenticate extends HttpServlet{  
    connectToDB con;
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        try {
            String username = req.getParameter("form-username");
            String password = req.getParameter("form-password");
            con = new connectToDB();
            if(con.connect()){
                Connection  connection = con.getConnection();
                Class.forName("com.mysql.jdbc.Driver");
                Statement stmt = connection.createStatement( ); 
                String SQL;
                SQL = "select * from db_bro.hospital";
                 //WHERE hospital_id=\""+username+"\" AND password=\""+password+"\"";
                ResultSet rs = stmt.executeQuery(SQL);
                if(rs.wasNull()){
                    displayMessage(res,"response in null");
                }
                boolean check = false;
                while ( rs.next( ) ) {
                    String db_username = rs.getString("hospital_id");
                    String db_password = rs.getString("password");
                    String db_hospital_name = rs.getString("hospital_name");
                    if((username == null ? db_username == null : username.equals(db_username)) && (password == null ? db_password == null : password.equals(db_password))){
                        check=true;
                        //displayMessage(res,"Authentication Success!");
                        try{
                                connection.close();
                            } catch (SQLException e) { /* ignored */}
                        
                        HttpSession session = req.getSession();
                        session.setAttribute( "hospital_id", db_username );
                        session.setAttribute( "hospital_name", db_hospital_name);
                        
                        res.sendRedirect("./home");
                        
                    }
                }
                if(!check){
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                    displayMessage(res,"Authentication Failed!");
                }
            }
            else{
                con.showErrormessage(res);
            }
            
            
            /*res.setContentType("text/html");//setting the content type
            PrintWriter pw=res.getWriter();//get the stream to write the data
            
            //writing html in the stream
            pw.println("<html><body>");
            pw.println("Welcome to servlet: "+username);
            pw.println("</body></html>");
            
            pw.close();//closing the stream
            */
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(authenticate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(authenticate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void displayMessage (HttpServletResponse res,String s) throws IOException{
        res.setContentType("text/html");//setting the content type
        PrintWriter pw=res.getWriter();//get the stream to write the data
        //writing html in the stream
        pw.println("<html><body>");
        pw.println("Info: "+s);
        pw.println("</body></html>");

        pw.close();//closing the stream
    }
}  
