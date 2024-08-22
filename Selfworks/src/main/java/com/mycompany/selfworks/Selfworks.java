package com.mycompany.selfworks;

import java.util.Scanner;

public class Selfworks {
 
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManagementSystem SMS = new StudentManagementSystem();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Search Student");
            System.out.println("6. Display Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    SMS.addStudent();
                    break;
                case 2:
                    SMS.editStudent();
                    break;
                case 3:
                    SMS.deleteStudent();
                    break;
                case 4:
                    SMS.sortStudents();
                    break;
                case 5:
                    SMS.searchStudent();
                    break;
                case 6:
                    SMS.displayStudents();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
