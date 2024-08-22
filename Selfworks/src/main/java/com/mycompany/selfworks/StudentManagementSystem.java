package com.mycompany.selfworks;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
     private static Stack<Student> deletedStudents = new Stack<>();
    private static Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        try {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            if (id.isBlank()) {
                System.out.println("ID cannot be blank.");
                return;
            }

            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Name cannot be blank.");
                return;
            }

            System.out.print("Enter marks: ");
            String marksInput = scanner.nextLine();
            if (marksInput.isBlank()) {
                System.out.println("Marks cannot be blank.");
                return;
            }

            float marks;
            try {
                marks = Float.parseFloat(marksInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for marks. Please enter a valid number.");
                return;
            }

            if (marks < 0 || marks > 10) {
                System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                return;
            }

            students.add(new Student(id, name, marks));
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void editStudent() {
        try {
            System.out.print("Enter ID of the student to edit: ");
            String id = scanner.nextLine();
            if (id.isBlank()) {
                System.out.println("ID cannot be blank.");
                return;
            }

            Student student = findStudentById(id);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Name cannot be blank.");
                return;
            }

            System.out.print("Enter new grade: ");
            String marksInput = scanner.nextLine();
            if (marksInput.isBlank()) {
                System.out.println("Marks cannot be blank.");
                return;
            }

            float marks;
            try {
                marks = Float.parseFloat(marksInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for marks. Please enter a valid number.");
                return;
            }

            if (marks < 0 || marks > 10) {
                System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                return;
            }

            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student details updated successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void deleteStudent() {
        try {
            System.out.print("Enter ID of the student to delete: ");
            String id = scanner.nextLine();
            if (id.isBlank()) {
                System.out.println("ID cannot be blank.");
                return;
            }

            Student student = findStudentById(id);
            if (student != null) {
                students.remove(student);
                deletedStudents.push(student);  // Push the deleted student onto the stack
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void restoreLastDeletedStudent() {
        if (deletedStudents.isEmpty()) {
            System.out.println("No students to restore.");
        } else {
            Student student = deletedStudents.pop();  // Pop the last deleted student
            students.add(student);
            System.out.println("Student restored: " + student);
        }
    }

    // Example method for checking if the stack is empty
    public void checkIfStackIsEmpty() {
        if (deletedStudents.isEmpty()) {
            System.out.println("The stack of deleted students is empty.");
        } else {
            System.out.println("The stack of deleted students is not empty.");
        }
    }

    public void sortStudents() {
        try {
            System.out.println("Sort by:");
            System.out.println("1. Grade");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Collections.sort(students, Comparator.comparingDouble(Student::getMarks).reversed());
                    System.out.println("Students sorted successfully by Grade (highest to lowest).");
                    displayStudents();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private String getRank(float marks) {
        if (marks >= 0.0 && marks < 5.0) {
            return "Fail";
        } else if (marks >= 5.0 && marks < 6.5) {
            return "Medium";
        } else if (marks >= 6.5 && marks < 7.5) {
            return "Good";
        } else if (marks >= 7.5 && marks < 9.0) {
            return "Very Good";
        } else if (marks >= 9.0 && marks <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid grade";
        }
    }

    public void searchStudent() {
    try {
        System.out.print("Enter ID of the student to search: ");
        String id = scanner.nextLine();
        if (id.isBlank()) {
            System.out.println("ID cannot be blank.");
            return;
        }
        students.stream()
            .peek(student -> System.out.println("Checking student: " + student.getId()))
            .filter(student -> student.getId().equals(id))
            .findFirst()
            .ifPresentOrElse(
                student -> System.out.println("Student found: " + student + ", Rank: " + getRank(student.getMarks())),
                () -> System.out.println("Student not found.")
            );
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student + ", Rank: " + getRank(student.getMarks()));
            }
        }
    }
}
