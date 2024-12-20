

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactManagementSystem1{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManagerGUI::new);
    }
}

class ContactManagerGUI {
    private final JFrame frame;
    private final DefaultTableModel tableModel;

    public ContactManagerGUI() {
        frame = new JFrame("Contact Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        String[] columnNames = {"Name", "Phone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable contactTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(contactTable);
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JButton addButton = new JButton("Add Contact");

        formPanel.setBorder(BorderFactory.createTitledBorder("Add New Contact"));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel()); 
        formPanel.add(addButton);


        JButton deleteButton = new JButton("Delete Selected Contact");

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tableModel.addRow(new Object[]{name, phone, email});
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
        });


        deleteButton.addActionListener(e -> {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
            }
        });

    
        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(deleteButton, BorderLayout.SOUTH);


        frame.setVisible(true);
    }
}
