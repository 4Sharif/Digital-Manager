/**
 * SearchEmployeeWindow.java
 * GUI for searching employees in the system.
 * Allows the user to search by name, SSN, or employee ID.
 * Displays a list of matching employees or an error message if no matches are found.
 */

 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;
 
 public class SearchEmployeeWindow extends JFrame implements ActionListener 
 {
     private JTextField searchField;
     private JButton searchButton;
 
     SearchEmployeeWindow() 
     {
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLayout(new BorderLayout());
         this.setSize(400, 200);
         this.setResizable(false);
  
         this.getContentPane().setBackground(Color.decode("#ff8080"));
 
         JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
         searchPanel.setBackground(Color.decode("#ff8080"));
         searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
 
         searchPanel.add(new JLabel("Search by Name, SSN, or Employee ID:"));
         searchField = new JTextField(20);
         searchPanel.add(searchField);
 
         searchButton = new JButton("Search");
         searchButton.setPreferredSize(new Dimension(150, 40));
         searchButton.addActionListener(this);
         searchPanel.add(searchButton);
 
         this.add(searchPanel, BorderLayout.CENTER);
 
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
             
             StringBuilder results = new StringBuilder();
             boolean found = false;
             
             for (Employee emp : EmployeeMod.getAllEmployees()) 
             {
                 if (emp.getName().equals(query) || emp.getSsn().equals(query) || 
                     String.valueOf(emp.getEmpId()).equals(query)) 
                 {
                     results.append(emp).append("\n");
                     found = true;
                 }
             }
             
             if (found) 
             {
                 JOptionPane.showMessageDialog(this, "Results:\n" + results);
             } 
             else 
             {
                 JOptionPane.showMessageDialog(this, "No employees found with exact match: " + query);
             }
         }
     }
 }
 
