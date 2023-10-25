package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    public static int HomeScreenAndOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Welcome to the Accounting Ledger application.");
        System.out.println("What would you like to do?");
        System.out.println("1. Add a Deposit.");
        System.out.println("2. Make a Payment(Debit).");
        System.out.println("3. Display Ledger.");
        System.out.println("4. Exit Application.");
        System.out.print("Please enter a number: ");
        int choice = 0;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
                    break;
                } else {
                    System.out.print("Please enter a valid choice between 1 and 4: ");
                }
            } catch (InputMismatchException ex) {
                System.out.print("Please enter a valid integer choice: ");
                scanner.next();
            }
        }
        return choice;
    }
}