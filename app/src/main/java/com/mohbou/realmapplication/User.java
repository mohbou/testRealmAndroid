package com.mohbou.realmapplication;



import io.realm.RealmObject;
import io.realm.annotations.Ignore;



public class User extends RealmObject {

    private double userId;

    private String lastName;
    private String firstName;

    @Ignore
    private  int sessionId;


    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", sessionId=" + sessionId +
                '}';
    }
}
