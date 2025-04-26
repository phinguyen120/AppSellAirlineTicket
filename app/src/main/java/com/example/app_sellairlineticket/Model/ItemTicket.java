package com.example.app_sellairlineticket.Model;

public class ItemTicket {
    private int sourceId;
    private String Title;
    private String Date;
    private int logocompany;
    private String price;

    public ItemTicket(int sourceId, String title, String date, int logocompany, String price) {
        this.sourceId = sourceId;
        Title = title;
        Date = date;
        this.logocompany = logocompany;
        this.price = price;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getLogocompany() {
        return logocompany;
    }

    public void setLogocompany(int logocompany) {
        this.logocompany = logocompany;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
