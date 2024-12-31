package views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
/**
 * Classe UserPanel
 * 
 * Cette classe représente une interface graphique pour la gestion des utilisateurs. 
 * Elle permet d'effectuer diverses actions comme rechercher, ajouter, modifier, 
 * supprimer et trier les utilisateurs via une table interactive.
 * 
 * Composants principaux :
 * - Champ de recherche avec un JComboBox pour le critère de recherche.
 * - Tableau pour afficher les utilisateurs.
 * - Boutons pour les opérations CRUD (Ajouter, Modifier, Supprimer) et tri.
 * 
 * Fonctionnalités :
 * - Recherche des utilisateurs par ID ou nom.
 * - Ajout, modification, suppression d'utilisateurs via des boutons dédiés.
 * - Tri des utilisateurs dans le tableau.
 * - Interface utilisateur moderne et facile à utiliser.
 * 
 * @version 1.0
 */
public class UserPanel extends JPanel {

    private JTextField searchField = new JTextField(20);
    private JButton searchButton = new JButton("🔍"); // Ajout de l'icône dans le bouton
    private JButton addButton = new JButton("Ajouter");
    private JButton editButton = new JButton("Modifier");
    private JButton deleteButton = new JButton("Supprimer");
    private JLabel searchLabel = new JLabel("Rechercher :");
    
    private JComboBox<String> searchCriteriaCombo = new JComboBox<>(new String[]{"ID", "Nom","Date"}); // JComboBox pour choisir le critère de recherche

    private String[] columns = {"ID", "Nom", "Prénom", "Droit d'accès", "Password", "Téléphone", "Email","Date d'ajout"};
    private Object[][] data = {}; // Mettez ici les données réelles à afficher
    private JTable table = new JTable(new DefaultTableModel(data, columns));
    /**
     * Constructeur de UserPanel.
     * 
     * Initialise l'interface graphique et ses composants :
     * - Ajoute les panneaux de recherche, tableau, et boutons.
     * - Configure les styles des composants pour une meilleure expérience utilisateur.
     */
 // Modifications dans le constructeur de UserPanel

    public UserPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);

        // Table customization
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(new Color(70, 130, 180)); // Same selection background as LivrePanel
        table.setSelectionForeground(Color.WHITE); // Same selection foreground color
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Top panel for search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(Color.WHITE);

        // Label and search field setup
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 30));

        // ComboBox for search criteria
        searchCriteriaCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        searchCriteriaCombo.setPreferredSize(new Dimension(100, 30)); // Adjust size

        // Search button setup
        searchPanel.setBackground(new Color(220, 225, 235)); // Light blue background
        searchLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        searchLabel.setForeground(new Color(80, 80, 100));

        // Add components to the search panel
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchCriteriaCombo);
        searchPanel.add(searchButton);

        // Table setup with scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Button panel setup
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.WHITE);

        // Centered buttons panel
        JPanel centerButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        centerButtonsPanel.setBackground(Color.WHITE);
        
        // Customize buttons (Add, Edit, Delete)
        customizeButton(addButton, "Ajouter");
        customizeButton(editButton, "Modifier");
        customizeButton(deleteButton, "Supprimer");
        centerButtonsPanel.add(addButton);
        centerButtonsPanel.add(editButton);
        centerButtonsPanel.add(deleteButton);

        // Add the centered buttons panel to the button panel
        buttonPanel.add(centerButtonsPanel, BorderLayout.CENTER);

        // Add all components to the main panel
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Utility to customize buttons with text
    private void customizeButton(JButton button, String text) {
        button.setText(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFont(new Font("Arial", Font.PLAIN, 14));
    }



    public JLabel getSearchLabel() {
		return searchLabel;
	}

	public void setSearchLabel(JLabel searchLabel) {
		this.searchLabel = searchLabel;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public void setEditButton(JButton editButton) {
		this.editButton = editButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public void setSearchCriteriaCombo(JComboBox<String> searchCriteriaCombo) {
		this.searchCriteriaCombo = searchCriteriaCombo;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTable getTable() {
        return table;
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

   

    public JComboBox<String> getSearchCriteriaCombo() {
        return searchCriteriaCombo;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }
    public String getSearchText() {
        return searchField.getText().trim(); // Récupère le texte de recherche
    }
}

