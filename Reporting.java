/**
 * Reporting.java
 * Provides methods to generate employee reports for:
 * - Full-time employee information
 * - Total pay grouped by job title
 * - Total pay grouped by division
 */

 import java.util.HashMap; 
 import java.util.Map;
 
 public class Reporting 
 {
     public static String generateFullTimeEmployeeReport() 
     {
         StringBuilder report = new StringBuilder("Full-Time Employee Information:\n");
         report.append("-------------------------------------------------\n");
         for (Employee emp : EmployeeMod.getAllEmployees()) 
         {
             report.append(emp.toString()).append("\n");
             report.append("-------------------------------------------------\n");
         }
         return report.toString();
     }
 
     public static Map<String, Double> getTotalPayByJobTitle() 
     {
         Map<String, Double> payByTitle = new HashMap<>();
         for (Employee emp : EmployeeMod.getAllEmployees()) 
         {
             String jobTitle = emp.getJobTitle();
             double currentPay = payByTitle.getOrDefault(jobTitle, 0.0);
             payByTitle.put(jobTitle, currentPay + emp.getSalary());
         }
         return payByTitle;
     }
 
     public static Map<String, Double> getTotalPayByDivision() 
     {
         Map<String, Double> payByDivision = new HashMap<>();
         for (Employee emp : EmployeeMod.getAllEmployees()) 
         {
             String division = emp.getDivision();
             double currentPay = payByDivision.getOrDefault(division, 0.0);
             payByDivision.put(division, currentPay + emp.getSalary());
         }
         return payByDivision;
     }
 }
 
