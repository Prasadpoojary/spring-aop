package com.springboot.aop.dto;

public class User
{
    int user_id;
    String user_name;
    String email;
    String mobile_number;
    String password;

    public User()
    {

    }

    public User(int user_id, String user_name, String email, String mobile_number, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
