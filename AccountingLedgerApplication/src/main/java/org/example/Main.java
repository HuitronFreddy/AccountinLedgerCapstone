package org.example;

import java.io.*;
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

                String date = transaction[0];
                String time = transaction[1];
                String description = transaction[2];
                String vendor = transaction[3];
                double amount = Double.parseDouble(transaction[4]);

                Ledger newTransaction = new Ledger(date, time, description, vendor, amount);
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
                        System.out.println("Please enter a valid choice between 1 and 4: ");
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
    static void addDeposit(ArrayList<Ledger> transactionList) {
        // Method for addDeposit
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following deposit information:");
        System.out.println("Enter the date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        System.out.println("Enter the time (Hour:Minute): ");
        String timeInput = scanner.nextLine();
        System.out.println("Enter a description: ");
        String userDescription = scanner.nextLine();
        System.out.println("Enter the vendor name: ");
        String vendorName = scanner.nextLine();
        System.out.println("Enter an amount: ");
        double depositAmount = scanner.nextDouble();

        Ledger newTransaction = new Ledger(dateInput, timeInput, userDescription, vendorName, depositAmount);
        transactionList.add(newTransaction);
        saveTransactionToCSV(newTransaction);
        System.out.println("Deposit successfully added. \n");
    }
    private static void saveTransactionToCSV(Ledger newTransaction) {
        try {
            File file = new File("src/main/resources/transactions.csv");

            boolean fileExists = file.exists();

            //if the file doesn't exist or is empty, write the header
            if (!fileExists || file.length() == 0) {
                FileWriter newTransactionWriter = new FileWriter("src/main/resources/transactions.csv");
                newTransactionWriter.write("Date|Time|Description|Vendor|Amount\n");
                newTransactionWriter.close();
            }
            //adding each new deposit to csv file
            FileWriter appendTransactionWriter = new FileWriter("src/main/resources/transactions.csv", true);
            appendTransactionWriter.write(
                    newTransaction.getDate() + "|" +
                            newTransaction.getTime() + "|" +
                            newTransaction.getDescription() + "|" +
                            newTransaction.getVendor() + "|" +
                            newTransaction.getAmount() + "\n"
            );
            appendTransactionWriter.close();
        } catch (IOException ex) {
            System.out.println("There's a problem with saving the transaction to CSV.");
        }
    }

    static <Transcation> void makePayment(ArrayList<Ledger> transactionList) {

    }
    static <Transcation> void displayLedger(ArrayList<Ledger> transactionList) {

    }
}