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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class addSession extends HttpServlet{  
    connectToDB con;
    
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException  
    {  
        try {
            String room_id = req.getParameter("room_id");
            String doctor_id = req.getParameter("doctor_id");
            String start_time = req.getParameter("start_time");
            String date_picked = req.getParameter("date_picked");
            con = new connectToDB();
            if(con.connect()){
                Connection  connection = con.getConnection();
                Class.forName("com.mysql.jdbc.Driver");
                Statement stmt = connection.createStatement( ); 
                String SQL,SQL1;
                SQL1 = "insert into appointmentz.session ( doctor_id, room_id, date, start_time) VALUES (?,?,?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
                    preparedStmt.setString (1, doctor_id);
                    preparedStmt.setString (2, room_id);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                    try {
                        java.util.Date d = formatter.parse(date_picked+"-"+start_time);
                        Date d_sql = new Date(d.getTime());
                        java.util.Date N  = new java.util.Date();
                        if(N.compareTo(d)>0){
                            res.sendRedirect("./error.jsp?error=Invalid Date!");
                        }
                        //String [] T = start_time.split(":");
                        //Time t = Time.valueOf(start_time);
                        //Time t = new Time(Integer.parseInt(T[0]),Integer.parseInt(T[1]),0);
                        
                        //java.sql.Time t_sql = new java.sql.Date(d.getTime());
                        preparedStmt.setString(4, start_time+":00");
                        preparedStmt.setDate(3, d_sql);
                    } catch (ParseException e) {
                            displayMessage(res,"Invalid Date!"+e.getLocalizedMessage());
                    }
                    

                // execute the preparedstatement
                preparedStmt.execute();
                
                SQL = "select * from appointmentz.session"; 
                ResultSet rs = stmt.executeQuery(SQL);
                
                if(rs.wasNull()){
                    displayMessage(res,"response in null");
                }
                boolean check = false;
                while ( rs.next( ) ) {
                    
                    String db_doctor_id = rs.getString("doctor_id");
                    String db_date_picked = rs.getString("date");
                    String db_start_time = rs.getString("start_time");
                    String db_room_id = rs.getString("room_id");
                        
                    if((doctor_id == null ? db_doctor_id == null : doctor_id.equals(db_doctor_id)) && (start_time == null ? db_start_time == null : (start_time+":00").equals(db_start_time)) && (room_id == null ? db_room_id == null : room_id.equals(db_room_id)) && (date_picked == null ? db_date_picked == null : date_picked.equals(db_date_picked))){
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
