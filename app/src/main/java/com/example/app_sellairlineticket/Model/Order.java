package com.example.app_sellairlineticket.Model;

import java.util.List;

public class Order {
      private String userId;
      private List<Customer> lists;
      private String airline;         // Tên hãng bay
       // Mã chuyến bay (tuỳ chọn nếu bạn có)
      private String departureTime;
      private String departureDate;// Giờ đi
      private String arrivalTime;    // Giờ đến
      private String from;           // Mã sân bay đi (ví dụ: HAN)
      private String to;
      private String total;
      private String dateOrder;

      public Order() {
      }

      public Order(String userId, List<Customer> lists, String airline, String departureTime, String departureDate, String arrivalTime, String from, String to, String total, String dateOrder) {
            this.userId = userId;
            this.lists = lists;
            this.airline = airline;
            this.departureTime = departureTime;
            this.departureDate = departureDate;
            this.arrivalTime = arrivalTime;
            this.from = from;
            this.to = to;
            this.total = total;
            this.dateOrder = dateOrder;
      }

      public String getUserId() {
            return userId;
      }

      public void setUserId(String userId) {
            this.userId = userId;
      }

      public List<Customer> getLists() {
            return lists;
      }

      public void setLists(List<Customer> lists) {
            this.lists = lists;
      }

      public String getAirline() {
            return airline;
      }

      public void setAirline(String airline) {
            this.airline = airline;
      }

      public String getDepartureTime() {
            return departureTime;
      }

      public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
      }

      public String getDepartureDate() {
            return departureDate;
      }

      public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
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

      public String getTotal() {
            return total;
      }

      public void setTotal(String total) {
            this.total = total;
      }

      public String getDateOrder() {
            return dateOrder;
      }

      public void setDateOrder(String dateOrder) {
            this.dateOrder = dateOrder;
      }
}
