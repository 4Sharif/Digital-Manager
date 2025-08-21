/**
 * Employee.java
 * Represents an employee in the Digital Manager system.
 * Stores information such as:
 * - Employee ID (auto-incremented)
 * - Name
 * - SSN
 * - Job Title
 * - Division
 * - Salary
 * Includes methods for getting and updating employee attributes, as well as displaying data.
 */

 public class Employee 
 {
     private static int idCounter = 1;
     private int empId;
     private String name;
     private String ssn;
     private String jobTitle;
     private String division;
     private double salary;
 
     public Employee(String name, String ssn, String jobTitle, String division, double salary) 
     {
         this.empId = idCounter++;
         this.name = name;
         this.ssn = ssn;
         this.jobTitle = jobTitle;
         this.division = division;
         this.salary = salary;
     }
 
     public int getEmpId() { return empId; }
     public String getName() { return name; }
     public void setName(String name) { this.name = name; }
     public String getSsn() { return ssn; }
     public String getJobTitle() { return jobTitle; }
     public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
     public String getDivision() { return division; }
     public void setDivision(String division) { this.division = division; }
     public double getSalary() { return salary; }
     public void setSalary(double salary) { this.salary = salary; }
 
     @Override
     public String toString() 
     {
         return "Employee [ID=" + empId + ", Name=" + name + ", SSN=" + ssn +
                ", Job Title=" + jobTitle + ", Division=" + division + ", Salary=" + salary + "]";
     }
 }
 
