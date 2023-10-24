package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.example.Ledger.displayLedger;

public class Main {
    public static void main(String[] args) {
        ArrayList<Ledger> transactionList = new ArrayList<>();

        //setting date and time formats
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/transactions.csv");
            Scanner scanner = new Scanner(fis);
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] transaction = line.split("\\|");

                // Parse date and time
                LocalDate date = LocalDate.parse(transaction[0], dateFormatter);
                LocalTime time = LocalTime.parse(transaction[1], timeFormatter);
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
            System.out.printf("Hello! Welcome to the Accounting Ledger application.%nWhat would you like to do?%n");
            System.out.println("1. Add a Deposit.");
            System.out.println("2. Make a Payment(Debit).");
            System.out.println("3. Display Ledger.");
            System.out.println("4. Exit Application.");
            System.out.println("Please enter a number: ");

            int homeScanner = 0;

            while (true) {
                try {
                    homeScanner = scanner.nextInt();
                    if (homeScanner >= 1 && homeScanner <= 4) {
                        break; // If a valid choice is selected, continue the app as normal; if invalid, the user will be asked for valid input
                    } else {
                        System.out.println("Please enter a valid choice between 1 and 4: ");
                    }
                } catch (InputMismatchException ex) { // Making sure the input is an integer; if it's a string, the user will be asked to input an integer
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following deposit information:");
        System.out.println("Enter the date (YYYY-MM-DD): ");
        LocalDate dateInput = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter the time (Hour:Minute AM/PM): ");
        LocalTime timeInput = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("h:mm a"));
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
            // If the file doesn't exist, write the header
            if (!fileExists) {
                FileWriter newTransactionWriter = new FileWriter("src/main/resources/transactions.csv");
                newTransactionWriter.write("Date|Time|Description|Vendor|Amount\n");
                newTransactionWriter.close();
            }
            // Adding each new deposit to the CSV file
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
    static void makePayment(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following payment information:");
        System.out.println("Enter the date (YYYY-MM-DD): ");
        LocalDate dateInput = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter the time (Hour:Minute AM/PM): ");
        LocalTime timeInput = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("h:mm a"));
        System.out.println("Enter a description: ");
        String userDescription = scanner.nextLine();
        System.out.println("Enter the vendor name: ");
        String vendorName = scanner.nextLine();
        System.out.println("Enter an amount: ");
        double paymentAmount = scanner.nextDouble();

        Ledger newTransaction = new Ledger(dateInput, timeInput, userDescription, vendorName, -paymentAmount);
        transactionList.add(newTransaction);
        saveTransactionToCSV(newTransaction);
        System.out.println("Payment successfully added. \n");
    }
}