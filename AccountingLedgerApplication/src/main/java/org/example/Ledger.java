package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Ledger {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Ledger(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    public String toString() {
        return String.format("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f",
                date, time, description, vendor, amount);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*static void displayAllEntries(ArrayList<Ledger> transactionList) {
        // Sort the transactionList by date in descending order (newest to oldest)
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display all entries from transactionList
        for (Ledger transaction : transactionList) {
            System.out.println(transaction);
        }
    }
    static void displayDeposits(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display deposits only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }
    static void displayPayments(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display payments or negative entries only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }
    static void runReport(ArrayList<Ledger> transactionList) {
        // report method here
    }*/
}
