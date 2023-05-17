package com.StealthStartup.StealthStartupAssigment;

import java.util.*;

class SATResult {
    private String name;
    private Address address;
    private int satScore;
    private String passed;

    public SATResult(String name, Address address, int satScore) {
        this.name = name;
        this.address = address;
        this.satScore = satScore;
        this.passed = satScore > 30 ? "Pass" : "Fail";
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getSatScore() {
        return satScore;
    }

    public void setSatScore(int satScore) {
        this.satScore = satScore;
        this.passed = satScore > 30 ? "Pass" : "Fail";
    }

    public String getPassed() {
        return passed;
    }
}

class Address {
    private String city;
    private String country;
    private String pincode;

    public Address(String city, String country, String pincode) {
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPincode() {
        return pincode;
    }
}

public class SATResultsApp {
    private static final Map<String, SATResult> satResults = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    insertData(scanner);
                    break;
                case 2:
                    viewAllData();
                    break;
                case 3:
                    getRank(scanner);
                    break;
                case 4:
                    updateScore(scanner);
                    break;
                case 5:
                    deleteRecord(scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Insert data");
        System.out.println("2. View all data");
        System.out.println("3. Get rank");
        System.out.println("4. Update score");
        System.out.println("5. Delete one record");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private static void insertData(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter City: ");
        String city = scanner.nextLine();

        System.out.print("Enter Country: ");
        String country = scanner.nextLine();

        System.out.print("Enter Pincode: ");
        String pincode = scanner.nextLine();

        System.out.print("Enter SAT Score: ");
        int satScore = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Address address = new Address(city, country, pincode);
        SATResult satResult = new SATResult(name, address, satScore);
        satResults.put(name, satResult);

        System.out.println("Data inserted successfully.");
    }

    private static void viewAllData() {
        if (satResults.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        for (SATResult satResult : satResults.values()) {
            System.out.println("Name: " + satResult.getName());
            System.out.println("Address: " + satResult.getAddress().getCity() + ", "
                    + satResult.getAddress().getCountry() + ", " + satResult.getAddress().getPincode());
            System.out.println("SAT Score: " + satResult.getSatScore());
            System.out.println("Passed: " + satResult.getPassed());
            System.out.println();
        }
    }

    private static void getRank(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        int rank = 1;
        boolean found = false;

        List<SATResult> sortedResults = new ArrayList<>(satResults.values());
        sortedResults.sort(Comparator.comparingInt(SATResult::getSatScore).reversed());

        for (SATResult result : sortedResults) {
            if (result.getName().equals(name)) {
                found = true;
                break;
            }
            rank++;
        }

        if (found) {
            System.out.println(name + " has rank " + rank + ".");
        } else {
            System.out.println("Record not found.");
        }
    }

    private static void updateScore(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        SATResult satResult = satResults.get(name);
        if (satResult != null) {
            System.out.print("Enter new SAT Score: ");
            int newScore = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            satResult.setSatScore(newScore);
            System.out.println("Score updated successfully.");
        } else {
            System.out.println("Record not found.");
        }
    }

    private static void deleteRecord(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        SATResult removedResult = satResults.remove(name);
        if (removedResult != null) {
            System.out.println("Record deleted successfully.");
        } else {
            System.out.println("Record not found.");
        }
    }
}


