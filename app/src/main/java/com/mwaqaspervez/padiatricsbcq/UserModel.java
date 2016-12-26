package com.mwaqaspervez.padiatricsbcq;

class UserModel {

    private String name, email, phone, password, time;

    UserModel(String name, String email, String phone, String password, String time) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
