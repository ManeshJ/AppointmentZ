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
//import java.util.Date;
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
public class deleteSession extends HttpServlet{  
    connectToDB con;
    
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        try {
            //String room_id = req.getParameter("room_id");
            //String doctor_id = req.getParameter("doctor_id");
            //String start_time = req.getParameter("start_time");
            //String date_picked = req.getParameter("date_picked");
            String session_id = req.getParameter("session_id");
            con = new connectToDB();
            if(con.connect()){
                Connection  connection = con.getConnection();
                Class.forName("com.mysql.jdbc.Driver");
                Statement stmt = connection.createStatement( );
                String SQL,SQL1;
                SQL1 = "delete from appointmentz.session where session_id = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
                    preparedStmt.setString (1, session_id);
                    
                    

                // execute the preparedstatement
                preparedStmt.execute();
                
                SQL = "select * from appointmentz.session"; 
                ResultSet rs = stmt.executeQuery(SQL);
                
                if(rs.wasNull()){
                    displayMessage(res,"response in null");
                }
                boolean check = false;
                while ( rs.next( ) ) {
                    
                    String db_session_id = rs.getString("session_id");
                    //String db_date_picked = rs.getString("date");
                    //String db_start_time = rs.getString("start_time");
                        
                    if((session_id == null ? db_session_id == null : session_id.equals(db_session_id))){
                        check=true;
                        //displayMessage(res,"Authentication Success!");
                    }
                }
                if(!check){
                        try {
                            connection.close();
                        } catch (SQLException e) { 
                            displayMessage(res,"SQLException:"+e);
                        }
                    res.sendRedirect("./sessions");
                    //displayMessage(res,"SQL query Failed!");
                }
            }
            else{
                con.showErrormessage(res);
            }
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
