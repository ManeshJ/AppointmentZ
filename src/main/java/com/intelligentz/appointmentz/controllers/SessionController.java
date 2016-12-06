package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.constants.RpiPinConstants;
import com.intelligentz.appointmentz.database.DBConnection;
import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.handler.RpiHandler;
import com.intelligentz.appointmentz.model.Button;
import com.intelligentz.appointmentz.model.Doctor;
import com.intelligentz.appointmentz.model.Room;
import com.intelligentz.appointmentz.model.Rpi;
import com.intelligentz.appointmentz.model.Session;
import com.intelligentz.appointmentz.model.SessonCustomer;
import org.apache.commons.dbutils.DbUtils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by lakshan on 11/16/16.
 */
public class SessionController {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    public Session getButtonSession(String buttonSerial) throws SQLException, ClassNotFoundException, IOException, PropertyVetoException, IllegalAccessException, InstantiationException {
        Session session = null;
        Button button = new ButtonController().getButtonBySerial(buttonSerial);
        Doctor doctor = new DoctorController().getDoctorById(button.getDoctor_id());
        session = getCurrentSessionOfDoctor(doctor);
        return session;
    }

    private Session getCurrentSessionOfDoctor(Doctor doctor) throws ClassNotFoundException, IOException, PropertyVetoException, InstantiationException, IllegalAccessException {
        Session session = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            df2.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            Date dateobj = new Date();
            String dateRep = df.format(dateobj);
            String timeRep = df2.format(dateobj);
            String SQL1 = "SELECT * FROM db_bro.session WHERE doctor_id = ? AND `date` = '"+dateRep+"' AND start_time < '"+timeRep+"' ORDER BY start_time DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, doctor.getDoctor_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String session_id = resultSet.getString("session_id");
                String room_id = resultSet.getString("room_id");
                String start_time = resultSet.getString("start_time");
                int current_no = resultSet.getInt("current_no");
                Room room = new RoomController().getRoomById(room_id);
                Rpi rpi = new RpiController().getRpiOfRoom(room.getRoom_id());
                ArrayList<SessonCustomer> sessonCustomers = new SessionCustomerController().getSessionCustomersForCurrentNumber(session_id,current_no+1);
                session = new Session(session_id,doctor,room,current_no,dateRep,start_time,rpi,sessonCustomers);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return session;
    }

    public int getCurrentNumberOfRoom(String room_id) throws ClassNotFoundException, SQLException, IOException, PropertyVetoException {
        int current_no = 0;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            df2.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            Date dateobj = new Date();
            String dateRep = df.format(dateobj);
            String timeRep = df2.format(dateobj);
            String SQL1 = "SELECT * FROM db_bro.session WHERE room_id = ? AND `date` = '"+dateRep+"' AND start_time < '"+timeRep+"' ORDER BY start_time DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, room_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                current_no = resultSet.getInt("current_no");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return current_no;
    }

    public void setCurrentNumberOfRoom(String room_id, int newNumber) throws ClassNotFoundException, IOException, PropertyVetoException {
        String session_id = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            df2.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            Date dateobj = new Date();
            String dateRep = df.format(dateobj);
            String timeRep = df2.format(dateobj);
            String SQL1 = "SELECT * FROM db_bro.session WHERE room_id = ? AND `date` = '"+dateRep+"' AND start_time < '"+timeRep+"' ORDER BY start_time DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, room_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                session_id = resultSet.getString("session_id");
                updateCurrentNumber(session_id,newNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
    }

    private void updateCurrentNumber(String session_id, int newNumber) throws ClassNotFoundException, SQLException, IOException, PropertyVetoException {
        try {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "UPDATE db_bro.session SET current_no = ? WHERE  session_id = ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setInt(1, newNumber);
            preparedStatement.setString(2, session_id);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
    }
//    public String getCurrentNumberOfRoom(String room_id) throws ClassNotFoundException, SQLException {
//        String session_id = null;
//        con = new connectToDB();
//        if(con.connect()) {
//            Connection connection = con.getConnection();
//            Class.forName("com.mysql.jdbc.Driver");
//            String SQL1;
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
//            Date dateobj = new Date();
//            String dateRep = df.format(dateobj);
//            String timeRep = df2.format(dateobj);
//            SQL1 = "SELECT * FROM appointmentz.session WHERE room_id = ? AND `date` = '"+dateRep+"' AND start_time < '"+timeRep+"' ORDER BY start_time DESC LIMIT 1";
//            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
//            preparedStmt.setString(1, room_id);
//            ResultSet rs = preparedStmt.executeQuery();
//            if (rs.next()) {
//                session_id = rs.getString("current_no");
//            }
//            connection.close();
//        }
//        return session_id;
//    }

    public void increaseSessionNumber(Session session) throws SQLException, ClassNotFoundException, IdeabizException, IOException, PropertyVetoException {
        updateCurrentNumber(session.getSession_id(),session.getCurrent_no()+1);
        new RpiHandler().updateRpiPin(session.getRpi().getSerial(),session.getRpi().getAuth(), RpiPinConstants.INTURRUPT_PIN, RpiPinConstants.ACTION_ON);
        new SMSController().sendSMS(session);
    }
    public void decreaseSessionNumber(String sessionId, int currentNumber) throws SQLException, ClassNotFoundException, IOException, PropertyVetoException {
        updateCurrentNumber(sessionId,currentNumber-1);
    }
}
