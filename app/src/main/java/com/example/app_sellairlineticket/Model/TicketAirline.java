package com.example.app_sellairlineticket.Model;

public class TicketAirline {
    private String airline;         // Tên hãng bay
    private String flightNumber;   // Mã chuyến bay (tuỳ chọn nếu bạn có)
    private String departureTime;  // Giờ đi
    private String arrivalTime;    // Giờ đến
    private String from;           // Mã sân bay đi (ví dụ: HAN)
    private String to;
    private String departureDate;// Mã sân bay đến (ví dụ: SGN)
    // Thời gian bay (ví dụ: 2h 5m)
    private String price;// Giá vé dạng chuỗi (VD: "1.608.968 VND")
    private String seatClass;
    public TicketAirline() {
        // Required default constructor for Firebase or deserialization
    }

    public TicketAirline(String airline, String flightNumber, String departureTime, String arrivalTime, String from, String to, String departureDate, String price, String seatClass) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.price = price;
        this.seatClass = seatClass;
    }

    public TicketAirline(String airline, String flightNumber, String departureTime, String arrivalTime, String from, String to, String price, String seatClass) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.price = price;
        this.seatClass = seatClass;
    }

    public TicketAirline(String airline, String flightNumber, String departureTime, String arrivalTime,
                         String from, String to, String price) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.price = price;
    }

    // Getter và Setter
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}

