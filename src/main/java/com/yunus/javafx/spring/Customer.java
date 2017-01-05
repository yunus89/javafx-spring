package com.yunus.javafx.spring;

public class Customer {
    private int id;
    private String firstName;
    private String secondName;
    private String phone;

    public Customer(int id, String firstName, String secondName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
