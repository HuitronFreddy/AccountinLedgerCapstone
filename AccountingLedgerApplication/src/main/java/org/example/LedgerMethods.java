package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LedgerMethods {
    static void displayAllEntries(ArrayList<Ledger> transactionList) {
        // Sort the transactionList by date in descending order (newest to oldest)
        transactionList.sort(Comparator.comparing(Ledger::getDate).reversed());
        // Display all entries from transactionList
        for (Ledger transaction : transactionList) {
            System.out.println(transaction);
        }
    }
    static void displayDeposits(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        transactionList.sort(Comparator.comparing(Ledger::getDate).reversed());
        // Display deposits only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }
    static void displayPayments(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        transactionList.sort(Comparator.comparing(Ledger::getDate).reversed());
        // Display payments or negative entries only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }
    static void runReport(ArrayList<Ledger> transactionList) {
        // report method here
    }
}
