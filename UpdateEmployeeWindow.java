/**
 * UpdateEmployeeWindow.java
 * GUI for updating employee details and adjusting salaries.
 * Features include:
 * - Searching for an employee by name, SSN, or employee ID.
 * - Updating employee details such as name and division.
 * - Increasing salaries by a percentage within a specified salary range.
 */

 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;
 
 public class UpdateEmployeeWindow extends JFrame implements ActionListener 
 {
     private JTextField searchField, newNameField, newDivisionField, percentageField, minSalaryField, maxSalaryField;
     private JButton searchButton, updateDetailsButton, updateSalaryButton;
     private Employee selectedEmployee = null;
 
     UpdateEmployeeWindow() 
     {
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLayout(new BorderLayout());
         this.setSize(500, 400);
         this.setResizable(false);
         this.getContentPane().setBackground(Color.decode("#b4b75c"));
 
         JPanel mainPanel = new JPanel();
         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
         mainPanel.setBackground(Color.decode("#b4b75c"));
         mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
 
         JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         searchPanel.setBackground(Color.decode("#b4b75c"));
         searchPanel.add(new JLabel("Search by Name, SSN, or Employee ID:"));
         searchField = new JTextField(20);
         searchPanel.add(searchField);
         searchButton = new JButton("Search");
         searchButton.addActionListener(this);
         searchPanel.add(searchButton);
         mainPanel.add(searchPanel);
 
         mainPanel.add(Box.createVerticalStrut(20));
 
         JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         detailsPanel.setBackground(Color.decode("#b4b75c"));
         detailsPanel.add(new JLabel("New Name:"));
         newNameField = new JTextField(20);
         detailsPanel.add(newNameField);
         detailsPanel.add(new JLabel("New Division:"));
         newDivisionField = new JTextField(20);
         detailsPanel.add(newDivisionField);
         updateDetailsButton = new JButton("Update Details");
         updateDetailsButton.addActionListener(this);
         detailsPanel.add(updateDetailsButton);
         mainPanel.add(detailsPanel);
 
         mainPanel.add(Box.createVerticalStrut(20));
 
         JPanel salaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         salaryPanel.setBackground(Color.decode("#b4b75c"));
         salaryPanel.add(new JLabel("Increase Salary by (%) for Range:"));
         salaryPanel.add(new JLabel("Min Salary:"));
         minSalaryField = new JTextField(10);
         salaryPanel.add(minSalaryField);
         salaryPanel.add(new JLabel("Max Salary:"));
         maxSalaryField = new JTextField(10);
         salaryPanel.add(maxSalaryField);
         salaryPanel.add(new JLabel("Percentage:"));
         percentageField = new JTextField(5);
         salaryPanel.add(percentageField);
         updateSalaryButton = new JButton("Update Salaries");
         updateSalaryButton.addActionListener(this);
         salaryPanel.add(updateSalaryButton);
         mainPanel.add(salaryPanel);
 
         this.add(mainPanel, BorderLayout.CENTER);
 
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }
 
     @Override
     public void actionPerformed(ActionEvent e) 
     {
         if (e.getSource() == searchButton) 
         {
             String query = searchField.getText().trim();
             if (query.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Please enter a search term.");
                 return;
             }
             
             selectedEmployee = null;
             for (Employee emp : EmployeeMod.getAllEmployees()) 
             {
                 if (emp.getName().equals(query) || emp.getSsn().equals(query) || 
                     String.valueOf(emp.getEmpId()).equals(query)) 
                 {
                     selectedEmployee = emp;
                     JOptionPane.showMessageDialog(this, "Employee Found:\n" + emp.toString());
                     return;
                 }
             }
             JOptionPane.showMessageDialog(this, "No matching employee found with exact match: " + query);
         } 
         else if (e.getSource() == updateDetailsButton) 
         {
             if (selectedEmployee != null) 
             {
                 String newName = newNameField.getText().trim();
                 String newDivision = newDivisionField.getText().trim();
                 
                 if (newName.isEmpty() && newDivision.isEmpty()) {
                     JOptionPane.showMessageDialog(this, "Please enter at least one field to update.");
                     return;
                 }
                 
                 boolean success = EmployeeMod.updateEmployee(selectedEmployee.getEmpId(), 
                     newName.isEmpty() ? null : newName, 
                     newDivision.isEmpty() ? null : newDivision);
                 
                 if (success) 
                 {
                     JOptionPane.showMessageDialog(this, "Employee details updated successfully!");
                     newNameField.setText("");
                     newDivisionField.setText("");
                 } 
                 else 
                 {
                     JOptionPane.showMessageDialog(this, "Error updating employee details.");
                 }
             } 
             else 
             {
                 JOptionPane.showMessageDialog(this, "Search for an employee first!");
             }
         } 
         else if (e.getSource() == updateSalaryButton) 
         {
             try 
             {
                 double minSalary = Double.parseDouble(minSalaryField.getText());
                 double maxSalary = Double.parseDouble(maxSalaryField.getText());
                 double percentageIncrease = Double.parseDouble(percentageField.getText());
                 
                 if (minSalary < 0 || maxSalary < 0 || percentageIncrease < 0) {
                     JOptionPane.showMessageDialog(this, "Please enter positive values.");
                     return;
                 }
                 
                 if (minSalary > maxSalary) {
                     JOptionPane.showMessageDialog(this, "Min salary cannot be greater than max salary.");
                     return;
                 }
                 
                 EmployeeMod.updateSalary(minSalary, maxSalary, percentageIncrease);
                 JOptionPane.showMessageDialog(this, "Salaries updated for the specified range!");
                 minSalaryField.setText("");
                 maxSalaryField.setText("");
                 percentageField.setText("");
             } 
             catch (NumberFormatException ex) 
             {
                 JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
             }
         }
     }
 }
 
 

