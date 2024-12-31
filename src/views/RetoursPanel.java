package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RetoursPanel extends JPanel {

    private JTextField searchField = new JTextField(20); // Search field
    private JButton searchButton = new JButton("🔍"); // Search button with icon
    private JLabel searchLabel = new JLabel("Rechercher :"); // Search label
    private JTable retourstable; // Table for displaying returns
    private JButton editButton = new JButton("Modifier");
    private JButton deleteButton = new JButton("Supprimer");

    public RetoursPanel() {
        // Set up main layout
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(220, 225, 235));

        // Configure search label
        searchLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        searchLabel.setForeground(new Color(80, 80, 100));

        // Configure search field
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 30));

        // Add components to search panel
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Configure table for returns
        String[] columns = {"ID", "ID Emprunt", "Date Retour Actuelle", "Retard"};
        Object[][] data = {}; // Initial empty data
        retourstable = new JTable(new DefaultTableModel(data, columns));
        retourstable.setFont(new Font("Arial", Font.PLAIN, 14));
        retourstable.setRowHeight(30);
        retourstable.setGridColor(Color.LIGHT_GRAY);
        retourstable.setSelectionBackground(new Color(70, 130, 180));
        retourstable.setSelectionForeground(Color.WHITE);
        retourstable.setDefaultEditor(Object.class, null); // Disable direct editing

        JScrollPane scrollPane = new JScrollPane(retourstable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // Create button panel for edit and delete operations
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Customize and add buttons
        customizeButton(editButton, "Modifier");
        buttonPanel.add(editButton);

        customizeButton(deleteButton, "Supprimer");
        buttonPanel.add(deleteButton);

        // Add panels to the main panel
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Utility method to customize buttons with text
    private void customizeButton(JButton button, String text) {
        button.setText(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(new Color(70, 130, 180));  // Similar blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    // Getters for components (to access them from other classes)
    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTable getRetourstable() {
        return retourstable;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
