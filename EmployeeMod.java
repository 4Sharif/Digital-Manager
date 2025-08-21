/**
 * EmployeeMod.java
 * Manages the employee list and provides operations for:
 * - Adding new employees
 * - Getting all employees
 * - Finding employees by ID
 * - Updating employee details
 * - Adjusting salaries within a specific range
 * - Deleting employees by ID
 */

 import java.util.ArrayList;
 import java.util.List;
 
 public class EmployeeMod 
 {
     private static List<Employee> employeeList = new ArrayList<>();
 
     public static void addEmployee(Employee employee) 
     {
         employeeList.add(employee);
     }
 
     public static List<Employee> getAllEmployees() 
     {
         return employeeList;
     }
 
     public static Employee findById(int id) 
     {
         for (Employee emp : employeeList) 
         {
             if (emp.getEmpId() == id) 
             {
                 return emp;
             }
         }
         return null;
     }
 
     public static boolean updateEmployee(int empId, String newName, String newDivision) 
     {
         Employee emp = findById(empId);
         if (emp != null) 
         {
             if (newName != null && !newName.isEmpty()) 
             {
                 emp.setName(newName);
             }
             if (newDivision != null && !newDivision.isEmpty()) 
             {
                 emp.setDivision(newDivision);
             }
             return true;
         }
         return false;
     }
 
     public static void updateSalary(double minSalary, double maxSalary, double percentageIncrease) 
     {
         for (Employee emp : employeeList) 
         {
             if (emp.getSalary() >= minSalary && emp.getSalary() <= maxSalary) 
             {
                 emp.setSalary(emp.getSalary() + emp.getSalary() * (percentageIncrease / 100));
             }
         }
     }
 
     public static boolean removeEmployee(int empId) 
     {
         return employeeList.removeIf(emp -> emp.getEmpId() == empId);
     }
 }
 
