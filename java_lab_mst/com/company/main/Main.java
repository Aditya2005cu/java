package com.company.main;

import com.company.employee.Employee;
import com.company.service.EmployeeService;
import com.company.exception.InvalidSalaryException;
import com.company.exception.EmployeeNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine();
                        
                        System.out.print("Enter Salary: ");
                        int salary = scanner.nextInt(); 

                        
                        if (salary < 10000 || salary < 0) {
                            throw new InvalidSalaryException("Salary must be 10,000 or greater.");
                        }

                        Employee newEmp = new Employee(id, name, dept, salary);
                        service.addEmployee(newEmp);

                    } catch (InvalidSalaryException e) {
                        System.out.println("\nValidation Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("\nInvalid input! Please enter correct data types.");
                        scanner.nextLine(); 
                    }
                    break;

                case 2:
                    service.displayEmployees();
                    break;

                case 3:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();
                    try {
                        service.searchEmployee(searchId);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("\nSearch Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Exiting System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}