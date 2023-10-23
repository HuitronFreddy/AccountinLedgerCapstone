package org.example;

public class Ledger {

    private int month;
    private int year;
    private String vendor;
    private double amount;

    public Ledger(int month, int year, String vendor, double amount) {
        this.month = month;
        this.year = year;
        this.vendor = vendor;
        this.amount = amount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
