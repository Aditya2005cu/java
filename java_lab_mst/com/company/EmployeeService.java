package com.company.service;

import com.company.employee.Employee;
import com.company.exception.EmployeeNotFoundException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeService {
    private final String FILE_NAME = "employees.txt";

    // Add employee to file
    public void addEmployee(Employee e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Format: id,name,department,salary
            String employeeData = e.getEmpid() + "," + e.getName() + "," + e.getDepartment() + "," + e.getSalary();
            writer.write(employeeData);
            writer.newLine();
            System.out.println("Employee added successfully to file!");
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    // Display all employees from file
    public void displayEmployees() {
        System.out.println("\n--- All Employees ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasData = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 4) {
                    // Recreate employee object from text data
                    Employee emp = new Employee(
                        Integer.parseInt(data[0]), 
                        data[1], 
                        data[2], 
                        Integer.parseInt(data[3])
                    );
                    emp.getDetails(); // Using your getDetails method
                    System.out.println("---------------------");
                    hasData = true;
                }
            }
            if (!hasData) {
                System.out.println("No employees found.");
            }
        } catch (IOException ex) {
            System.out.println("No existing records found. File will be created upon adding an employee.");
        }
    }

    // Search employee by ID
    public void searchEmployee(int searchId) throws EmployeeNotFoundException {
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (Integer.parseInt(data[0]) == searchId) {
                    System.out.println("\n--- Employee Found ---");
                    Employee emp = new Employee(
                        Integer.parseInt(data[0]), 
                        data[1], 
                        data[2], 
                        Integer.parseInt(data[3])
                    );
                    emp.getDetails();
                    found = true;
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        }

        if (!found) {
            throw new EmployeeNotFoundException("Employee with ID " + searchId + " was not found in the system.");
        }
    }
}