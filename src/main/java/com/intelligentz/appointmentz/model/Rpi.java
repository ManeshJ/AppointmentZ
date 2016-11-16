package com.intelligentz.appointmentz.model;

/**
 * Created by lakshan on 11/16/16.
 */
public class Rpi {
    private String serial;
    private String auth;

    public Rpi() {
    }

    public Rpi(String serial, String auth) {
        this.serial = serial;
        this.auth = auth;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
