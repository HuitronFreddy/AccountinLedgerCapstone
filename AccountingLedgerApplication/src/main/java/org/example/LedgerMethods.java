package org.example;

import java.time.LocalDate;
import java.util.*;

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
        Scanner scanner = new Scanner(System.in);
        boolean isMakingSelection = true;

        while (isMakingSelection) {
            System.out.println("Welcome to the Report Screen.");
            System.out.println("1. Month to Date.");
            System.out.println("2. Previous Month.");
            System.out.println("3. Year to Date.");
            System.out.println("4. Previous Year.");
            System.out.println("5. Search by Vendor.");
            System.out.println("6. Custom Search.");
            System.out.println("7. Back.");
            System.out.print("Please enter a number: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    monthToDate(transactionList);
                    break;
                case 2:
                    previousMonth(transactionList);
                    break;
                case 3:
                    yearToDate(transactionList);
                    break;
                case 4:
                    previousYear(transactionList);
                    break;
                case 5:
                    searchByVendor(transactionList);
                    break;
                case 6:
                    customSearch(transactionList);
                    break;
                case 7:
                    isMakingSelection = false;
                    break;
            }
        }
    }
    //for our reports screen we are going to create a new array list of transactions which will hold filtered searches
    //now when we print out our transactionlist we can specifically print filtered transactions only
    static void monthToDate(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if (transaction.getDate().getMonth() == currentDate.getMonth()) {
                filteredTransactions.add(transaction);
            }
        }
        displayTransactions(filteredTransactions);
    }
    static void previousMonth(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate endDateOfPreviousMonth = currentDate.withDayOfMonth(1).minusDays(1);

        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.isAfter(startDateOfPreviousMonth) && transactionDate.isBefore(endDateOfPreviousMonth)) {
                filteredTransactions.add(transaction);
            }
        }
        displayTransactions(filteredTransactions);
    }
    static void yearToDate(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if (transaction.getDate().getYear() == currentDate.getYear()) {
                filteredTransactions.add(transaction);
            }
        }
        displayTransactions(filteredTransactions);
    }
    static void previousYear(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfPreviousYear = currentDate.minusYears(1).withDayOfYear(1);
        LocalDate endDateOfPreviousYear = currentDate.withDayOfYear(1).minusDays(1);

        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.isAfter(startDateOfPreviousYear) && transactionDate.isBefore(endDateOfPreviousYear)) {
                filteredTransactions.add(transaction);
            }
        }
        displayTransactions(filteredTransactions);
    }
    static void searchByVendor(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the vendor name: ");
        String vendorName = scanner.nextLine();

        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                filteredTransactions.add(transaction);
            }
        }
        displayTransactions(filteredTransactions);
    }
    static void customSearch(ArrayList<Ledger> transactions){
        Scanner scanner = new Scanner(System.in);
        LocalDate startDate = null;
        LocalDate endDate = null;
        String description;
        String vendor;
        double amount = -1;

        //Search prompts
        System.out.print("Enter start date (yyyy-MM-dd) or leave blank: ");
        String startDateInput = scanner.nextLine();
        if (!startDateInput.isEmpty()) {
            startDate = LocalDate.parse(startDateInput);
        }

        System.out.print("Enter end date (yyyy-MM-dd) or leave blank: ");
        String endDateInput = scanner.nextLine();
        if (!endDateInput.isEmpty()) {
            endDate = LocalDate.parse(endDateInput);
        }

        System.out.print("Enter description or leave blank: ");
        description = scanner.nextLine();

        System.out.print("Enter vendor or leave blank: ");
        vendor = scanner.nextLine();

        System.out.print("Enter amount or leave blank: ");
        String amountInput = scanner.nextLine();
        if (!amountInput.isEmpty()) {
            amount = Double.parseDouble(amountInput);
        }

        List<Ledger> filteredTransactions = new ArrayList<>();
        //for loop to determine filters
        for (Ledger transaction : transactions) {
            boolean isMatch = true;

            if (startDate != null && !transaction.getDate().isAfter(startDate)) {
                isMatch = false;
            }

            if (endDate != null && !transaction.getDate().isBefore(endDate)) {
                isMatch = false;
            }

            if (description != null && !transaction.getDescription().contains(description)) {
                isMatch = false;
            }

            if (vendor != null && !transaction.getVendor().equalsIgnoreCase(vendor)) {
                isMatch = false;
            }

            if (amount != -1 && Math.abs(transaction.getAmount() - amount) >= 0.01) {
                isMatch = false;
            }

            if (isMatch) {
                filteredTransactions.add(transaction);
            }
        }
        displayTransactions(filteredTransactions);
    }
    static void displayTransactions(List<Ledger> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("Transactions:");
        for (Ledger transaction : transactions) {
            System.out.println("Date: " + transaction.getDate());
            System.out.println("Time: " + transaction.getTime());
            System.out.println("Description: " + transaction.getDescription());
            System.out.println("Vendor: " + transaction.getVendor());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("------------------------");
        }
    }
}
