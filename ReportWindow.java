/**
 * ReportWindow.java
 * GUI for generating and displaying employee reports.
 * Reports include:
 * - Full-time employee information
 * - Total pay by job title
 * - Total pay by division
 * Displays the reports in a text area.
 * Allows downloading reports as CSV or PDF and uploading CSV files.
 */

 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.io.*;
 import java.util.Map;
 import javax.swing.*;
 import javax.swing.filechooser.FileNameExtensionFilter;
 
 public class ReportWindow extends JFrame implements ActionListener 
 {
     private JButton fullTimeReportButton, payByTitleButton, payByDivisionButton;
     private JButton downloadCsvButton, downloadPdfButton, uploadCsvButton;
     private JTextArea reportArea;
 
     ReportWindow() 
     {
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLayout(new BorderLayout());
         this.setSize(700, 600);
         this.setResizable(false);
         this.getContentPane().setBackground(Color.decode("#80ff80"));
 
         // Title panel
         JPanel titlePanel = new JPanel();
         titlePanel.setBackground(Color.decode("#80ff80"));
         titlePanel.add(new JLabel("Employee Reports", SwingConstants.CENTER));
         this.add(titlePanel, BorderLayout.NORTH);
 
         // Report area
         reportArea = new JTextArea();
         reportArea.setEditable(false);
         reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
         JScrollPane scrollPane = new JScrollPane(reportArea);
         this.add(scrollPane, BorderLayout.CENTER);
 
         // Button panels
         JPanel reportButtonPanel = new JPanel(new FlowLayout());
         reportButtonPanel.setBackground(Color.decode("#80ff80"));
         fullTimeReportButton = new JButton("Full-Time Employee Info");
         payByTitleButton = new JButton("Total Pay by Job Title");
         payByDivisionButton = new JButton("Total Pay by Division");
 
         fullTimeReportButton.addActionListener(this);
         payByTitleButton.addActionListener(this);
         payByDivisionButton.addActionListener(this);
 
         reportButtonPanel.add(fullTimeReportButton);
         reportButtonPanel.add(payByTitleButton);
         reportButtonPanel.add(payByDivisionButton);
 
         JPanel fileButtonPanel = new JPanel(new FlowLayout());
         fileButtonPanel.setBackground(Color.decode("#80ff80"));
         downloadCsvButton = new JButton("Download CSV");
         downloadPdfButton = new JButton("Download PDF");
         uploadCsvButton = new JButton("Upload CSV");
 
         downloadCsvButton.addActionListener(this);
         downloadPdfButton.addActionListener(this);
         uploadCsvButton.addActionListener(this);
 
         fileButtonPanel.add(downloadCsvButton);
         fileButtonPanel.add(downloadPdfButton);
         fileButtonPanel.add(uploadCsvButton);
 
         // Combine button panels
         JPanel allButtonPanel = new JPanel(new BorderLayout());
         allButtonPanel.setBackground(Color.decode("#80ff80"));
         allButtonPanel.add(reportButtonPanel, BorderLayout.NORTH);
         allButtonPanel.add(fileButtonPanel, BorderLayout.SOUTH);
 
         this.add(allButtonPanel, BorderLayout.SOUTH);
 
         // Center the window on screen
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }
 
     @Override
     public void actionPerformed(ActionEvent e) 
     {
         if (e.getSource() == fullTimeReportButton) 
         {
             String report = Reporting.generateFullTimeEmployeeReport();
             reportArea.setText(report);
         } 
         else if (e.getSource() == payByTitleButton) 
         {
             Map<String, Double> payByTitle = Reporting.getTotalPayByJobTitle();
             StringBuilder report = new StringBuilder("Total Pay by Job Title:\n");
             for (Map.Entry<String, Double> entry : payByTitle.entrySet()) 
             {
                 report.append("Job Title: ").append(entry.getKey())
                       .append(", Total Pay: $").append(String.format("%.2f", entry.getValue())).append("\n");
             }
             reportArea.setText(report.toString());
         } 
         else if (e.getSource() == payByDivisionButton) 
         {
             Map<String, Double> payByDivision = Reporting.getTotalPayByDivision();
             StringBuilder report = new StringBuilder("Total Pay by Division:\n");
             for (Map.Entry<String, Double> entry : payByDivision.entrySet()) 
             {
                 report.append("Division: ").append(entry.getKey())
                       .append(", Total Pay: $").append(String.format("%.2f", entry.getValue())).append("\n");
             }
             reportArea.setText(report.toString());
         }
         else if (e.getSource() == downloadCsvButton) 
         {
             downloadReportAsCsv();
         }
         else if (e.getSource() == downloadPdfButton) 
         {
             downloadReportAsPdf();
         }
         else if (e.getSource() == uploadCsvButton) 
         {
             uploadCsvFile();
         }
     }
 
     private void downloadReportAsCsv() {
         if (reportArea.getText().isEmpty()) {
             JOptionPane.showMessageDialog(this, "Generate a report first before downloading.");
             return;
         }
 
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Save CSV Report");
         fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
         fileChooser.setSelectedFile(new File("employee_report.csv"));
 
         int result = fileChooser.showSaveDialog(this);
         if (result == JFileChooser.APPROVE_OPTION) {
             File file = fileChooser.getSelectedFile();
             if (!file.getName().toLowerCase().endsWith(".csv")) {
                 file = new File(file.getAbsolutePath() + ".csv");
             }
 
             try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                 String[] lines = reportArea.getText().split("\n");
                 for (String line : lines) {
                     if (!line.trim().isEmpty()) {
                         writer.println(line);
                     }
                 }
                 JOptionPane.showMessageDialog(this, "CSV file saved successfully!");
             } catch (IOException ex) {
                 JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
             }
         }
     }
 
     private void downloadReportAsPdf() {
         if (reportArea.getText().isEmpty()) {
             JOptionPane.showMessageDialog(this, "Generate a report first before downloading.");
             return;
         }
 
         JOptionPane.showMessageDialog(this, "PDF download functionality requires additional libraries.\n" +
             "For now, you can copy the report text and save it manually.");
     }
 
     private void uploadCsvFile() {
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Upload CSV File");
         fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
 
         int result = fileChooser.showOpenDialog(this);
         if (result == JFileChooser.APPROVE_OPTION) {
             File file = fileChooser.getSelectedFile();
             try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                 StringBuilder content = new StringBuilder();
                 String line;
                 while ((line = reader.readLine()) != null) {
                     content.append(line).append("\n");
                 }
                 reportArea.setText(content.toString());
                 JOptionPane.showMessageDialog(this, "CSV file loaded successfully!");
             } catch (IOException ex) {
                 JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
             }
         }
     }
 }
 
 
