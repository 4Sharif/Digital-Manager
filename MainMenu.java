/**
 * MainMenu.java
 * The main navigation window for the Digital Manager.
 * Provides buttons to access different features, including:
 * - Adding a new employee
 * - Searching for an employee
 * - Updating employee details
 * - Generating employee reports
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 
 public class MainMenu extends JFrame implements ActionListener 
 {
     private JButton addButton, searchButton, updateButton, reportButton;
 
     MainMenu() 
     {
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setLayout(new BorderLayout());
         this.setSize(600, 400);
         this.setResizable(false);
  
         this.getContentPane().setBackground(Color.decode("#80dfff"));
 
         JPanel titlePanel = new JPanel();
         titlePanel.setBackground(Color.decode("#80dfff"));
         JLabel titleLabel = new JLabel("Digital Manager");
         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
         titlePanel.add(titleLabel);
         this.add(titlePanel, BorderLayout.NORTH);
 
         JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
         buttonPanel.setBackground(Color.decode("#80dfff"));
         buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
 
         addButton = new JButton("Add Employee");
         addButton.setPreferredSize(new Dimension(200, 50));
 
         searchButton = new JButton("Search Employee");
         searchButton.setPreferredSize(new Dimension(200, 50));
 
         updateButton = new JButton("Update Employee");
         updateButton.setPreferredSize(new Dimension(200, 50));
 
         reportButton = new JButton("Generate Reports");
         reportButton.setPreferredSize(new Dimension(200, 50));
 
         addButton.addActionListener(this);
         searchButton.addActionListener(this);
         updateButton.addActionListener(this);
         reportButton.addActionListener(this);
 
         buttonPanel.add(addButton);
         buttonPanel.add(searchButton);
         buttonPanel.add(updateButton);
         buttonPanel.add(reportButton);
 
         this.add(buttonPanel, BorderLayout.CENTER);
 
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }
 
     @Override
     public void actionPerformed(ActionEvent e) 
     {
         if (e.getSource() == addButton) 
         {
             new AddEmployeeWindow();
         } 
         else if (e.getSource() == searchButton) 
         {
             new SearchEmployeeWindow();
         } 
         else if (e.getSource() == updateButton) 
         {
             new UpdateEmployeeWindow();
         } 
         else if (e.getSource() == reportButton) 
         {
             new ReportWindow();
         }
     }
 }
 
