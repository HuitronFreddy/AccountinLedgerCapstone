package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ledger> transactionList = FileHandlers.readTransactionsFromCSV();

        while (true) {
            int choice = UserInput.HomeScreenAndOptions();
            switch (choice) {
                case 1:
                    HomeScreenMethods.addDeposit((ArrayList<Ledger>) transactionList);
                    break;
                case 2:
                    HomeScreenMethods.makePayment((ArrayList<Ledger>) transactionList);
                    break;
                case 3:
                    HomeScreenMethods.displayLedger((ArrayList<Ledger>) transactionList);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}