package com.example.sqlite;

public class Person {
    private String pname;
    private String pphone;

//    int icon;

    private int id;

    public Person(String pname, String pphone, int id) {
        this.pname = pname;
        this.pphone = pphone;
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
