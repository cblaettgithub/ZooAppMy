package com.example.kevinschmidt.zooapp.classes;

/**
 * Created by chris on 17.03.2016.
 */
public class EmployeeEntry {
    public EmployeeEntry(){};
    public EmployeeEntry(String username,
                         String password, String firstname,
                         String lastname, String birthdate) {

        Username = username;
        Password = password;
        Firstname = firstname;
        Lastname = lastname;
        Birthdate = birthdate;
    }

    private int IdEmployee;
    private String Username;
    private String Password;
    private String Firstname;
    private String Lastname;
    private String Birthdate;


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

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

    public int getIdEmployee() {
        return IdEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        IdEmployee = idEmployee;
    }
}
