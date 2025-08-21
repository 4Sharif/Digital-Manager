/**
 * AddEmployeeWindow.java
 * GUI for adding a new employee to the system.
 * This window allows the user to input details such as:
 * - Name
 * - SSN
 * - Job Title
 * - Division
 * - Salary
 * Once the form is submitted, the new employee is added to the system backend.
 */

 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;
 
 public class AddEmployeeWindow extends JFrame implements ActionListener 
 {
     private JTextField nameField, ssnField, jobTitleField, salaryField, divisionField;
     private JButton submitButton;
 
     AddEmployeeWindow() 
     {
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLayout(new BorderLayout());
         this.setSize(400, 300);
         this.setResizable(false);
  
         this.getContentPane().setBackground(Color.decode("#cc80ff"));
 
         JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
         formPanel.setBackground(Color.decode("#cc80ff"));
         formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
 
         formPanel.add(new JLabel("Name:"));
         nameField = new JTextField();
         formPanel.add(nameField);
 
         formPanel.add(new JLabel("SSN:"));
         ssnField = new JTextField();
         formPanel.add(ssnField);
 
         formPanel.add(new JLabel("Job Title:"));
         jobTitleField = new JTextField();
         formPanel.add(jobTitleField);
 
         formPanel.add(new JLabel("Division:"));
         divisionField = new JTextField();
         formPanel.add(divisionField);
 
         formPanel.add(new JLabel("Salary:"));
         salaryField = new JTextField();
         formPanel.add(salaryField);
 
         this.add(formPanel, BorderLayout.CENTER);
 
         JPanel buttonPanel = new JPanel();
         buttonPanel.setBackground(Color.decode("#cc80ff"));
         submitButton = new JButton("Add Employee");
         submitButton.setPreferredSize(new Dimension(150, 40));
         submitButton.addActionListener(this);
         buttonPanel.add(submitButton);
         this.add(buttonPanel, BorderLayout.SOUTH);
 
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }
 
     @Override
     public void actionPerformed(ActionEvent e) 
     {
         if (e.getSource() == submitButton) 
         {
             try 
             {
                 String name = nameField.getText();
                 String ssn = ssnField.getText();
                 String jobTitle = jobTitleField.getText();
                 String division = divisionField.getText();
                 double salary = Double.parseDouble(salaryField.getText());
 
                 EmployeeMod.addEmployee(new Employee(name, ssn, jobTitle, division, salary));
                 JOptionPane.showMessageDialog(this, "Employee Added Successfully!");
                 this.dispose();
             } 
             catch (Exception ex) 
             {
                 JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
             }
         }
     }
 }
 
