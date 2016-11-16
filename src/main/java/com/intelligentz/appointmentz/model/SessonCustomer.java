package com.intelligentz.appointmentz.model;

/**
 * Created by lakshan on 11/16/16.
 */
public class SessonCustomer {
    private String mobile;
    private int appointment_num;

    public SessonCustomer() {
    }

    public SessonCustomer(String mobile, int appointment_num) {
        this.mobile = mobile;
        this.appointment_num = appointment_num;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAppointment_num() {
        return appointment_num;
    }

    public void setAppointment_num(int appointment_num) {
        this.appointment_num = appointment_num;
    }
}
