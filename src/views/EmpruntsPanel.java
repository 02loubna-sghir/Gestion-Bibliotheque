package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmpruntsPanel extends JPanel {

    private JTextField searchField = new JTextField(20); // Champ de recherche
    private JButton searchButton = new JButton("🔍"); // Bouton avec icône de recherche
    private JLabel searchLabel = new JLabel("Rechercher :"); // Label "Rechercher"
    private JTable empruntstable;
    private JButton returnButton = new JButton("Retourner Livre");
    private JButton editButton = new JButton("Modifier");
    private JButton deleteButton = new JButton("Supprimer");
    private JButton prolongButton = new JButton("Prolonger");

    public EmpruntsPanel() {
        // Configuration du layout principal
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Panneau de recherche
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(220, 225, 235));

        // Configuration du label "Rechercher"
        searchLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        searchLabel.setForeground(new Color(80, 80, 100));

        // Configuration du champ de recherche
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composants au panneau de recherche
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Configuration de la table
        String[] columns = {"ID", "ID Utilisateur", "Titre Livre", "Date Emprunt", "Date Retour"};
        Object[][] data = {}; // Données initiales (vide)
        empruntstable = new JTable(new DefaultTableModel(data, columns));
        empruntstable.setFont(new Font("Arial", Font.PLAIN, 14));
        empruntstable.setRowHeight(30);
        empruntstable.setGridColor(Color.LIGHT_GRAY);
        empruntstable.setSelectionBackground(new Color(70, 130, 180));
        empruntstable.setSelectionForeground(Color.WHITE);
        empruntstable.setDefaultEditor(Object.class, null); // Désactiver l'édition directe

        JScrollPane scrollPane = new JScrollPane(empruntstable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // Panneau des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Personnalisation et ajout des boutons
        customizeButton(returnButton, "Retourner Livre");
        buttonPanel.add(returnButton);

        customizeButton(editButton, "Modifier");
        buttonPanel.add(editButton);

        customizeButton(deleteButton, "Supprimer");
        buttonPanel.add(deleteButton);

        customizeButton(prolongButton, "Prolonger");
        buttonPanel.add(prolongButton);

        // Ajout des panneaux au panneau principal
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Méthode utilitaire pour personnaliser les boutons avec texte
    private void customizeButton(JButton button, String text) {
        button.setText(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(new Color(70, 130, 180));  // Couleur bleu similaire
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    // Getters pour les composants (facilite l'accès depuis d'autres classes)
    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTable getEmpruntstable() {
        return empruntstable;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getProlongButton() {
        return prolongButton;
    }
}
