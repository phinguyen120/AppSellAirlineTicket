package com.example.app_sellairlineticket.Model;

import java.util.Date;

public class Flight {
    private String flightId;          // Mã chuyến bay (ví dụ: "VN123")
    private String departureAirport;  // Sân bay đi (vd: "HAN - Nội Bài")
    private String arrivalAirport;    // Sân bay đến (vd: "SGN - Tân Sơn Nhất")
    private String departureTime;     // Giờ khởi hành
    private String comeTime;          // giờ đến
    private String  departureDatetime;  // NGÀY KHỞI HÀNH
    private String airline;           // Hãng bay (vd: "Vietnam Airlines")
    private String ticketClass;       // Hạng vé (vd: "Phổ thông", "Thương gia")
    private String price;             // Giá vé (vd: "1.999.000 VND")
    private boolean available;        // Còn chỗ hay không
    private String iconName;          // Tên icon hãng bay (drawable)

    public Flight() {
    }

    public Flight(String flightId, String departureAirport, String arrivalAirport, String departureTime, String comeTime, String departureDatetime, String airline, String ticketClass, String price, boolean available, String iconName) {
        this.flightId = flightId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.comeTime = comeTime;
        this.departureDatetime = departureDatetime;
        this.airline = airline;
        this.ticketClass = ticketClass;
        this.price = price;
        this.available = available;
        this.iconName = iconName;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getComeTime() {
        return comeTime;
    }

    public void setComeTime(String comeTime) {
        this.comeTime = comeTime;
    }

    public String getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(String departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}

