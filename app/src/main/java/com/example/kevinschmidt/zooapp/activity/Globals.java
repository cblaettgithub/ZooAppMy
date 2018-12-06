package com.example.kevinschmidt.zooapp.activity;

import android.app.Application;

/**
 * Created by chris on 26.03.2016.
 */
public class Globals extends Application {
    public Globals() {    }

    private String Username;
    //private String Password;
    private String Firstname;
    private String Lastname;
    private String Birthdate;

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }


}
