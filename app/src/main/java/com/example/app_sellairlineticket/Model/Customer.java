package com.example.app_sellairlineticket.Model;

public class Customer {
    private String fullname;
    private String phone;
    private String email;

    public Customer() {
    }

    public Customer(String name, String phone, String email) {
        this.fullname = name;
        this.phone = phone;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
