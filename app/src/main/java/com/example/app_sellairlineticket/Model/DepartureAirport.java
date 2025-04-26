package com.example.app_sellairlineticket.Model;

public class DepartureAirport {
    private String code;
    private String name;

    public DepartureAirport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
