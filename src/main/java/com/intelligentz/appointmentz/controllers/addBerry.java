/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.connectToDB;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ndine
 */
public class addBerry extends HttpServlet{  
    connectToDB con;
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        try {
            String room_id = req.getParameter("room_id");
            String auth = req.getParameter("auth");
            String serial = req.getParameter("serial");
            con = new connectToDB();
            if(con.connect()){
                Connection  connection = con.getConnection();
                Class.forName("com.mysql.jdbc.Driver");
                Statement stmt = connection.createStatement( ); 
                String SQL,SQL1;
                SQL1 = "insert into appointmentz.rpi ( room_id, auth, serial) VALUES (?,?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
                    preparedStmt.setString (1, room_id);
                    preparedStmt.setString (2, auth);
                    preparedStmt.setString (3, serial);
                // execute the preparedstatement
                preparedStmt.execute();

                SQL = "select * from appointmentz.rpi"; 
                ResultSet rs = stmt.executeQuery(SQL);
                
                if(rs.wasNull()){
                    displayMessage(res,"response in null");
                }
                boolean check = false;
                while ( rs.next( ) ) {
                    
                    String db_auth = rs.getString("auth");
                    String db_serial = rs.getString("serial");
                    String db_room_id = rs.getString("room_id");
                        
                    if((auth == null ? db_auth == null : auth.equals(db_auth)) && (room_id == null ? db_room_id == null : room_id.equals(db_room_id)) && (serial == null ? db_serial == null : serial.equals(db_serial))){
                        check=true;
                        //displayMessage(res,"Authentication Success!");
                        
                            try {
                                connection.close();
                            } catch (SQLException e) { 
                                displayMessage(res,"SQLException");
                            }
                        
                        res.sendRedirect("./home");
                        
                    }
                }
                if(!check){
                    
                        try {
                            connection.close();
                        } catch (SQLException e) { 
                            displayMessage(res,"SQLException");
                        }
                    displayMessage(res,"SQL query Failed!");
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
        } catch (ClassNotFoundException | SQLException ex) {
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
