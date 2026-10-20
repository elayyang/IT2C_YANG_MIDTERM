
package com.yourdomain.ui;

import com.yourdomain.dao.ScenarioDao;
import com.yourdomain.model.ScenarioEntity;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final ScenarioDao dao = new ScenarioDao();

    public void displayMenu() {
        System.out.println("\n==============================================");
        System.out.println(" VETERINARY PET REGISTRATION & VACCINATION");
        System.out.println("==============================================");
        System.out.println("1. Add Vaccination Record");
        System.out.println("2. View Vaccination Records");
        System.out.println("3. Exit System");
        System.out.println("==============================================");
        System.out.print("Enter choice: ");
    }

    public int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void addRecord() {
        System.out.println("\n--- ADD VACCINATION RECORD ---");

        String petName = getInput("Enter pet name: ");
        String species = getInput("Enter species (Dog/Cat/etc.): ");
        String vaccine = getInput("Enter vaccine: ");

        double fee;
        while (true) {
            System.out.print("Enter vaccination fee: ");

            try {
                fee = Double.parseDouble(scanner.nextLine().trim());

                if (fee < 0) {
                    System.out.println("Fee cannot be negative.");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid fee. Please enter a number.");
            }
        }

        ScenarioEntity record = new ScenarioEntity(
                0, petName, species, vaccine, fee
        );

        if (dao.addRecord(record)) {
            System.out.println("Vaccination record added successfully!");
        } else {
            System.out.println("Failed to add vaccination record.");
        }
    }

    public void viewRecords() {
        System.out.println("\n--- VACCINATION RECORDS ---");

        List<ScenarioEntity> records = dao.getAllRecords();

        if (records.isEmpty()) {
            System.out.println("No vaccination records found.");
            return;
        }

        System.out.printf(
                "%-5s %-18s %-12s %-20s %10s%n",
                "ID", "PET NAME", "SPECIES", "VACCINE", "FEE"
        );

        System.out.println(
                "---------------------------------------------------------------------"
        );

        for (ScenarioEntity record : records) {
            System.out.printf(
                    "%-5d %-18s %-12s %-20s %10.2f%n",
                    record.id(),
                    record.petName(),
                    record.species(),
                    record.vaccine(),
                    record.fee()
            );
        }

        System.out.println("Total records: " + records.size());
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
