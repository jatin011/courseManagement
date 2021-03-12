package com.example.MsProject.auth.Model;

public class User {
    private String firstName;
    private String lastName;
    private String emailId;
    private int userId;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

    public User(){}

//    public User(String firstName, String lastName, String emailId) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailId = emailId;
//    }
//
//    public User(String firstName, String lastName, String emailId, int userId) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailId = emailId;
//        this.userId = userId;
//    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
