package com.mycompany.selfworks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter marks: ");
        float marks = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    public void editStudent() {
        System.out.print("Enter ID of the student to edit: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);

        if (student != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new grade: ");
            float marks = scanner.nextFloat();
            scanner.nextLine(); // Consume newline

            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student details updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent() {
        System.out.print("Enter ID of the student to delete: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void sortStudents() {
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
        System.out.print("Enter ID of the student to search: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);

        if (student != null) {
            System.out.println("Student found: " + student + ", Rank: " + getRank(student.getMarks()));
        } else {
            System.out.println("Student not found.");
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
