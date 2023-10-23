package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Ledger> transactionList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/transactions.csv");
            Scanner scanner = new Scanner(fis);
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] transaction = line.split("\\|");

                int month = Integer.parseInt(transaction[0]);
                int year = Integer.parseInt(transaction[1]);
                String vendor = transaction[2];
                double amount = Double.parseDouble(transaction[3]);

                Ledger newTransaction = new Ledger(month, year, vendor, amount);
                transactionList.add(newTransaction);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Your file is not there");
        }


        boolean isMakingSelection = true;
        Scanner scanner = new Scanner(System.in);
        while (isMakingSelection) {
            System.out.printf("Hello! Welcome to the Accounting Ledger application. %nWhat would you like to do? %n");
            System.out.println("1. Add a Deposit.");
            System.out.println("2. Make a Payment(Debit).");
            System.out.println("3. Display Ledger.");
            System.out.println("4. Exit Application.");
            System.out.println("Please enter a number: ");

            int homeScanner = 0;

            while (true) {
                try {
                    homeScanner = scanner.nextInt(); //read choice from user
                    if (homeScanner >= 1 && homeScanner <= 4) {
                        break; //if a valid choice is selected continue app as normal, if invalid user will be asked for a valid input
                    } else {
                        System.out.println("Please enter a valid choice between 1 and 4.");
                    }
                } catch (InputMismatchException ex) { //making sure input is an integer if string user will be asked to input an integer
                    System.out.println("Please enter a valid integer choice: ");
                    scanner.next();
                }
            }
            switch (homeScanner) {
                case 1:
                    addDeposit(transactionList);
                    break;
                case 2:
                    makePayment(transactionList);
                    break;
                case 3:
                    displayLedger(transactionList);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
    private static void addDeposit(ArrayList<Ledger> transactionList) {
        //method for addDeposit
    }

    private static void makePayment(ArrayList<Ledger> transactionList) {
        // method for makepayment
    }

    private static void displayLedger(ArrayList<Ledger> transactionList) {
        // method for displayLedger
    }
}