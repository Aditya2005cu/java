package com.company.employee;

public class Employee { 
    private int empid;
    private String name;
    private String department;
    private int salary;

    public Employee(int empid,String name,String department,int salary){
        this.empid=empid;
        this.name=name;
        this.department=department;
        this.salary=salary;
    }
    
    public void getDetails(){
        System.out.println("Employee ID: "+empid);
        System.out.println("Name: "+name);
        System.out.println("Department: "+department);
        System.out.println("Salary: "+salary);
    }

    public void updateSalary(int newSalary){
        this.salary=newSalary;
    }
    public void updateDepartment(String newDepartment){
        this.department=newDepartment;
    }

    public int getEmpid() { return empid; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getSalary() { return salary; }

}
