package com.gohil.rupesh.androidpractical.Activity;

/**
 * Created by Aru on 16-07-2017.
 */

public class Population {

    public String id;
    public String name;
    public String phone_number;
    public String subject;
    public Population(String id, String name, String phone_number,String subject) {

        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setsubject(String subject) {
        this.subject = subject;
    }

    public Population() {

    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getphone_number() {
        return phone_number;
    }

    public void setphone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
