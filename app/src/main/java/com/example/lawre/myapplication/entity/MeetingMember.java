package com.example.lawre.myapplication.entity;

/**
 * Created by haoguang on 12/27/2016.
 */

public class MeetingMember {

    private User user;
    private String role;

    public MeetingMember(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MeetingMember{" +
                "user=" + user +
                ", role='" + role + '\'' +
                '}';
    }
}
