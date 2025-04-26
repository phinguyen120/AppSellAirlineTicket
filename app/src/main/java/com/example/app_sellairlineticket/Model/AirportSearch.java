package com.example.app_sellairlineticket.Model;

import java.io.Serializable;

public class AirportSearch implements Serializable {
    private int imgSource;
    private String name;
    private String code;
    private String location;

    public AirportSearch(int imgSource, String name, String code, String location) {
        this.imgSource = imgSource;
        this.name = name;
        this.code = code;
        this.location = location;
    }

    public int getImgSource() {
        return imgSource;
    }

    public void setImgSource(int imgSource) {
        this.imgSource = imgSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
