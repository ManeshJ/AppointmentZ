package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.constants.RpiPinConstants;
import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.handler.RpiHandler;
import com.intelligentz.appointmentz.model.Button;
import com.intelligentz.appointmentz.model.Doctor;
import com.intelligentz.appointmentz.model.Room;
import com.intelligentz.appointmentz.model.Rpi;
import com.intelligentz.appointmentz.model.Session;
import com.intelligentz.appointmentz.model.SessonCustomer;
import com.mysql.jdbc.Connection;
import com.sun.research.ws.wadl.Doc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lakshan on 11/16/16.
 */
public class SessionController {
    connectToDB con;
    public Session getButtonSession(String buttonSerial) throws SQLException, ClassNotFoundException {
        Session session = null;
        Button button = new ButtonController().getButton(buttonSerial);
        Doctor doctor = new DoctorController().getDoctor(button.getDoctor_id());
        session = getCurrentSessionOfDoctor(doctor);
        return session;
    }

    private Session getCurrentSessionOfDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
        Session session = null;
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            Date dateobj = new Date();
            String dateRep = df.format(dateobj);
            String timeRep = df2.format(dateobj);
            SQL1 = "SELECT * FROM appointmentz.session WHERE doctor_id = ? AND `date` = '"+dateRep+"' AND start_time < '"+timeRep+"' ORDER BY start_time DESC LIMIT 1";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, doctor.getDoctor_id());
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                String session_id = rs.getString("session_id");
                String room_id = rs.getString("room_id");
                String start_time = rs.getString("start_time");
                int current_no = rs.getInt("current_no");
                Room room = new RoomController().getRoom(room_id);
                Rpi rpi = new RpiController().getRpiOfRoom(room.getRoom_id());
                ArrayList<SessonCustomer> sessonCustomers = new SessionCustomerController().getSessionCustomers(session_id,current_no+1);
                session = new Session(session_id,doctor,room,current_no,dateRep,start_time,rpi,sessonCustomers);
            }
            connection.close();
        }
        return session;
    }

    public int getCurrentSessionIdOfRoom(String room_id) throws ClassNotFoundException, SQLException {
        int current_no = 0;
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            Date dateobj = new Date();
            String dateRep = df.format(dateobj);
            String timeRep = df2.format(dateobj);
            SQL1 = "SELECT * FROM appointmentz.session WHERE room_id = ? AND `date` = '"+dateRep+"' AND start_time < '"+timeRep+"' ORDER BY start_time DESC LIMIT 1";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, room_id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                current_no = rs.getInt("current_no");
            }
            connection.close();
        }
        return current_no;
    }
    private void updateCurrentNumber(String session_id, int newNumber) throws ClassNotFoundException, SQLException {
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            SQL1 = "UPDATE appointmentz.session SET current_no = ? WHERE  session_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setInt(1, newNumber);
            preparedStmt.setString(2, session_id);
            preparedStmt.execute(SQL1);
            connection.close();
        }
    }


    public void increaseSessionNumber(Session session) throws SQLException, ClassNotFoundException, IdeabizException {
        updateCurrentNumber(session.getSession_id(),session.getCurrent_no()+1);
        new RpiHandler().updateRpiPin(session.getRpi().getSerial(),session.getRpi().getAuth(), RpiPinConstants.INTURRUPT_PIN, RpiPinConstants.ACTION_ON);
        new SMSController().sendSMS(session);
    }
    public void decreaseSessionNumber(String sessionId, int currentNumber) throws SQLException, ClassNotFoundException {
        updateCurrentNumber(sessionId,currentNumber-1);
    }
    public void resetSessionNumber(String sessionId) throws SQLException, ClassNotFoundException {
        updateCurrentNumber(sessionId,0);
    }



}
