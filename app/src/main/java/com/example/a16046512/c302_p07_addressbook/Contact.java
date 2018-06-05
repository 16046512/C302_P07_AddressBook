package com.example.a16046512.c302_p07_addressbook;

public class Contact {
    int id;
    String firstname,lastname,home,mobile;

    public Contact(int id, String firstname, String lastname, String home, String mobile) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.home = home;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

}
