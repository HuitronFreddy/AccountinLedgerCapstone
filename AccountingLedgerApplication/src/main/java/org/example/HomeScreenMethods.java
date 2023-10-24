package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.FileHandlers.saveTransactionToCSV;
import static org.example.LedgerMethods.*;

public class HomeScreenMethods {
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
    static void displayLedger(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        boolean isMakingSelection = true;

        while (isMakingSelection) {
            System.out.println("Welcome to the Ledger Menu.");
            System.out.println("1. Display all entries.");
            System.out.println("2. Display Deposits.");
            System.out.println("3. Display Payments.");
            System.out.println("4. Run Report.");
            System.out.println("5. Go back to the home screen.");
            System.out.print("Please enter a number: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllEntries(transactionList);
                    break;
                case 2:
                    displayDeposits(transactionList);
                    break;
                case 3:
                    displayPayments(transactionList);
                    break;
                case 4:
                    runReport(transactionList);
                    break;
                case 5:
                    isMakingSelection = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
