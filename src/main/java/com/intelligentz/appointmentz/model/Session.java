package com.intelligentz.appointmentz.model;

import java.util.ArrayList;

/**
 * Created by lakshan on 11/16/16.
 */
public class Session {
    private String session_id;
    private Doctor doctor;
    private Room room;
    private int current_no;
    private String date;
    private String start_time;
    private Rpi rpi;
    private ArrayList<SessonCustomer> sessonCustomers;

    public Session() {
    }

    public Session(String session_id, Doctor doctor, Room room, int current_no, String date, String start_time, Rpi rpi) {
        this.session_id = session_id;
        this.doctor = doctor;
        this.room = room;
        this.current_no = current_no;
        this.date = date;
        this.start_time = start_time;
        this.rpi = rpi;
    }

    public Session(String session_id, Doctor doctor, Room room, int current_no, String date, String start_time, Rpi rpi, ArrayList<SessonCustomer> sessonCustomers) {
        this.session_id = session_id;
        this.doctor = doctor;
        this.room = room;
        this.current_no = current_no;
        this.date = date;
        this.start_time = start_time;
        this.rpi = rpi;
        this.sessonCustomers = sessonCustomers;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getCurrent_no() {
        return current_no;
    }

    public void setCurrent_no(int current_no) {
        this.current_no = current_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Rpi getRpi() {
        return rpi;
    }

    public void setRpi(Rpi rpi) {
        this.rpi = rpi;
    }

    public ArrayList<SessonCustomer> getSessonCustomers() {
        return sessonCustomers;
    }

    public void setSessonCustomers(ArrayList<SessonCustomer> sessonCustomers) {
        this.sessonCustomers = sessonCustomers;
    }
}
