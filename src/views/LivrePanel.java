package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LivrePanel extends JPanel {

    private JTextField searchField = new JTextField(20); // Champ de recherche
    private JButton searchButton = new JButton("🔍"); // Bouton avec icône de recherche
    private JLabel searchLabel = new JLabel("Rechercher :"); // Label "Rechercher"
    private JTable livresTable;
    private JButton addButton = new JButton("Ajouter Livre");
    private JButton editButton = new JButton("Modifier");
    private JButton deleteButton = new JButton("Supprimer");
    private JButton emprunterButton = new JButton("Emprunter");

    public LivrePanel() {
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
        String[] columns = {"ID", "Titre", "Auteur", "Année", "Genre", "ISBN", "Éditeur", "Langue", "Disponibilité"};
        Object[][] data = {}; // Données initiales (vide)
        livresTable = new JTable(new DefaultTableModel(data, columns));
        livresTable.setFont(new Font("Arial", Font.PLAIN, 14));
        livresTable.setRowHeight(30);
        livresTable.setGridColor(Color.LIGHT_GRAY);
        livresTable.setSelectionBackground(new Color(70, 130, 180));
        livresTable.setSelectionForeground(Color.WHITE);
        livresTable.setDefaultEditor(Object.class, null); // Désactiver l'édition directe

        JScrollPane scrollPane = new JScrollPane(livresTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // Panneau des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Personnalisation et ajout des boutons
        customizeButton(addButton, "Ajouter Livre");
        buttonPanel.add(addButton);

        customizeButton(editButton, "Modifier");
        buttonPanel.add(editButton);

        customizeButton(deleteButton, "Supprimer");
        buttonPanel.add(deleteButton);

        customizeButton(emprunterButton, "Emprunter");
        buttonPanel.add(emprunterButton);

        // Ajout des panneaux au panneau principal
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Méthode utilitaire pour personnaliser les boutons avec texte
    private void customizeButton(JButton button, String text) {
        button.setText(text);
        button.setPreferredSize(new Dimension(120, 40));
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

    public JTable getLivresTable() {
        return livresTable;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEmprunterButton() {
        return emprunterButton;
    }
}
