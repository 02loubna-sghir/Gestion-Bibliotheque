package controllers;
import java.time.LocalDate;
import exceptions.*;
import models.*;
import views.*;

import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Contrôleur pour la gestion des utilisateurs.
 * Cette classe est responsable de l'interaction entre la vue et le modèle des utilisateurs.
 */

public class UtilisateurController {
    private UtilisateurModel model = new UtilisateurModel("utilisateurs.csv");
    private UserPanel view = new UserPanel();
    /**
     * Constructeur du contrôleur pour la gestion des utilisateurs.
     * 
     * @param globalView Vue principale de l'application contenant l'onglet pour la gestion des utilisateurs.
     */
    public UtilisateurController(GlobalView globalView) {
        // Ajouter l'onglet "Gestion des Utilisateurs"
        globalView.getTabbedPane().addTab("Gestion des Utilisateurs", view);
        model.lireCSV();
        updateTable();
        // Ajouter l'écouteur d'action pour le bouton "Ajouter"
        view.getAddButton().addActionListener(e -> ajouter());
        view. getEditButton().addActionListener(e -> modifier());
        view.getDeleteButton().addActionListener(e -> supprimer());
        view.getSearchButton().addActionListener(e -> rechercher());
        view.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = view.getTable().columnAtPoint(e.getPoint());

                // Vérifier si la colonne cliquée est celle de "Date Emprunt"
                if (column == 0) { // 1 correspond à l'index de la colonne "Id"
                	trierParId();
                } 
                else if (column == 1) { // 3 correspond à l'index de la colonne 
                    trierParNom();
                }
                else if (column == 2) { // 3 correspond à l'index de la colonne 
                    trierParPrenom();
                }
                else if (column == 3) { // 3 correspond à l'index de la colonne 
                    trierParDroit();
                } 
                else if (column == 4) { // 3 correspond à l'index de la colonne 
                    trierParPassword();
                } 
                else if (column == 5) { // 3 correspond à l'index de la colonne 
                    trierParTelephone();
                } else if (column == 6) { // 3 correspond à l'index de la colonne 
                    trierParEmail();
                } else if (column == 7) { // 3 correspond à l'index de la colonne 
                    trierParDate();
                }
                else {
                    JOptionPane.showMessageDialog(view, "Tri non implémenté pour cette colonne.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    public void trierParNom(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParNom();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Nom.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Nom: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    public void trierParId(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParId();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Id.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Id: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    
    
    public void trierParPrenom(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParPrenom();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Prénom.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Prénom: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    
    public void trierParDroit(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParDroitAcces();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Droit d'accès.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Droit d'accès: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    public void trierParPassword(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParPassword();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Mot de passe.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Nom: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    public void trierParTelephone(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParNumeroTel();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Numéro téléphone.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Numéro téléphone: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    public void trierParEmail(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParEmail();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Email.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Email: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    public void trierParDate(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierParDate();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Date d'ajout.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Date d'ajout: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    
    public void updateTable() {
    List<Utilisateur> utilisateurs= model.getListe();
    DefaultTableModel tableModel = view.getTableModel();
    tableModel.setRowCount(0);
    for (Utilisateur utilisateur : utilisateurs) {
        tableModel.addRow(new Object[]{utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(),utilisateur.getDroitAcces(),utilisateur.getPassword(),
                                       utilisateur.getNumerotel(),utilisateur.getEmail(),utilisateur.getDateUtilisateur() });
    	}
    
    
    }
    /**
     * Ouvre un formulaire pour ajouter un nouvel utilisateur.
     * Les champs de saisie sont validés avant de créer un nouvel utilisateur et de l'ajouter au modèle.
     */
    //ajouter
    public void ajouter() {
        // Créer un nouveau JPanel pour les champs du formulaire
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        // Ajouter les champs de saisie (Nom, Prénom, Email, etc.)
        formPanel.add(new JLabel("Nom:"));
        JTextField nomField = new JTextField(15);
        formPanel.add(nomField);

        formPanel.add(new JLabel("Prénom:"));
        JTextField prenomField = new JTextField(15);
        formPanel.add(prenomField);

        formPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField(15);
        formPanel.add(emailField);

        formPanel.add(new JLabel("Droit d'accès:"));
        JTextField droitAccesField = new JTextField(15);
        formPanel.add(droitAccesField);

        formPanel.add(new JLabel("Mot de passe:"));
        JPasswordField passwordField = new JPasswordField(15);
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Numéro de téléphone:"));
        JTextField numeroTelField = new JTextField(15);
        formPanel.add(numeroTelField);

        // Afficher la boîte de dialogue pour récupérer les informations
        int result = JOptionPane.showConfirmDialog(
                view,
                formPanel,
                "Ajouter un utilisateur",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Si l'utilisateur clique sur OK, traiter les données
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Récupérer et valider les champs
                String nom = nomField.getText().trim();//trim() pour supprimer les espaces, les tabulations ou les retours à la ligne
                String prenom = prenomField.getText().trim();
                String email = emailField.getText().trim();
                String droitAcces = droitAccesField.getText().trim();
                String password = new String(passwordField.getPassword());
                String numeroTelStr = numeroTelField.getText().trim();

                // Vérifier que tous les champs sont saisis
                if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || droitAcces.isEmpty() || password.isEmpty() || numeroTelStr.isEmpty()) {
                    throw new IllegalArgumentException("Tous les champs doivent être remplis.");
                }

                // Validation de l'adresse e-mail avec une expression régulière
                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new IllegalArgumentException("Adresse e-mail invalide.");
                }

                // Vérifier que le numéro de téléphone est un entier positif
                int numeroTel;
                try {
                    numeroTel = Integer.parseInt(numeroTelStr);
                    if (numeroTel <= 0) {
                        throw new IllegalArgumentException("Le numéro de téléphone doit être un entier positif.");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Le numéro de téléphone doit être un entier.");
                }
                LocalDate dateUtilisateur = LocalDate.now();
                // Créer un nouvel utilisateur
                Utilisateur utilisateur = new Utilisateur(nom, prenom, droitAcces, password, numeroTel, email,dateUtilisateur);

                // Vérifier si l'utilisateur existe déjà
                try {
                    model.ajouterUtilisateur(utilisateur);

                    // Ajouter les données au modèle de la table si l'utilisateur est ajouté avec succès
                    DefaultTableModel tableModel = view.getTableModel();
                    tableModel.addRow(new Object[]{
                        utilisateur.getId(), nom, prenom, droitAcces, password, numeroTel, email,dateUtilisateur
                    });

                    // Afficher un message de succès
                    JOptionPane.showMessageDialog(view, "Utilisateur ajouté avec succès !");
                } catch (UtilisateurExsiste e) {
                    // Afficher un message d'erreur si l'utilisateur existe déjà
                    JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException e) {
                // Afficher un message d'erreur spécifique
                JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        model.sauvegarderCSV();
    }
    
    /**
     * Ouvre un formulaire pour modifier un utilisateur sélectionné dans la table.
     * Les informations existantes sont pré-remplies et peuvent être modifiées.
     */
    //modifier
    public void modifier() {
    	 int selectedRow = view.getTable().getSelectedRow();
    	    if (selectedRow == -1) {
    	        JOptionPane.showMessageDialog(view, "Veuillez sélectionner un utilisateur à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
    	        return;
    	    }

        // Créer un nouveau JPanel pour les champs du formulaire
    	    DefaultTableModel tableModel = view.getTableModel();
    	    int id = (int) tableModel.getValueAt(selectedRow, 0);
    	    String nom = (String) tableModel.getValueAt(selectedRow, 1);
    	    String prenom = (String) tableModel.getValueAt(selectedRow, 2);
    	    String droitAcces = (String) tableModel.getValueAt(selectedRow, 3);
    	    String password = (String) tableModel.getValueAt(selectedRow, 4);
    	    int numeroTel = (int) tableModel.getValueAt(selectedRow, 5);
    	    String email = (String) tableModel.getValueAt(selectedRow, 6);
    	    LocalDate date=(LocalDate) tableModel.getValueAt(selectedRow, 7);
    	    // Créer un panneau de formulaire avec les champs pré-remplis
    	    JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
    	    formPanel.setBackground(Color.WHITE);

    	    formPanel.add(new JLabel("Nom:"));
    	    JTextField nomField = new JTextField(nom, 15);
    	    formPanel.add(nomField);

    	    formPanel.add(new JLabel("Prénom:"));
    	    JTextField prenomField = new JTextField(prenom, 15);
    	    formPanel.add(prenomField);

    	    formPanel.add(new JLabel("Email:"));
    	    JTextField emailField = new JTextField(email, 15);
    	    formPanel.add(emailField);

    	    formPanel.add(new JLabel("Droit d'accès:"));
    	    JTextField droitAccesField = new JTextField(droitAcces, 15);
    	    formPanel.add(droitAccesField);

    	    formPanel.add(new JLabel("Mot de passe:"));
    	    JPasswordField passwordField = new JPasswordField(password, 15);
    	    formPanel.add(passwordField);

    	    formPanel.add(new JLabel("Numéro de téléphone:"));
    	    JTextField numeroTelField = new JTextField(String.valueOf(numeroTel), 15);
    	    formPanel.add(numeroTelField);

    	    // Afficher la boîte de dialogue pour modifier l'utilisateur
    	    int result = JOptionPane.showConfirmDialog(
    	            view,
    	            formPanel,
    	            "Modifier un utilisateur",
    	            JOptionPane.OK_CANCEL_OPTION,
    	            JOptionPane.PLAIN_MESSAGE
    	    );

    	    // Si l'utilisateur clique sur OK, traiter les données
    	    if (result == JOptionPane.OK_OPTION) {
    	        try {
    	            // Récupérer et valider les champs
    	            nom = nomField.getText().trim();
    	            prenom = prenomField.getText().trim();
    	            email = emailField.getText().trim();
    	            droitAcces = droitAccesField.getText().trim();
    	            password = new String(passwordField.getPassword());
    	            String numeroTelStr = numeroTelField.getText().trim();

    	            // Vérifier que tous les champs sont remplis
    	            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || droitAcces.isEmpty() || password.isEmpty() || numeroTelStr.isEmpty()) {
    	                throw new IllegalArgumentException("Tous les champs doivent être remplis.");
    	            }

    	            // Validation de l'adresse e-mail avec une expression régulière
    	            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
    	                throw new IllegalArgumentException("Adresse e-mail invalide.");
    	            }

    	            // Vérifier que le numéro de téléphone est un entier positif
    	            int numeroTel1;
    	            try {
    	                numeroTel1 = Integer.parseInt(numeroTelStr);
    	                if (numeroTel1 <= 0) {
    	                    throw new IllegalArgumentException("Le numéro de téléphone doit être un entier positif.");
    	                }
    	            } catch (NumberFormatException e) {
    	                throw new IllegalArgumentException("Le numéro de téléphone doit être un entier.");
    	            }
    	            LocalDate dateUtilisateur = LocalDate.now();
    	            // Créer un nouvel utilisateur avec les informations modifiées
    	            Utilisateur utilisateur = new Utilisateur( nom, prenom, droitAcces, password, numeroTel1, email,dateUtilisateur);

    	            // Mettre à jour l'utilisateur dans le modèle
    	            try {
    	            model.modifierUtilisateur(id, nom, prenom, droitAcces, password, numeroTel1, email,date);

    	            // Mettre à jour la ligne dans le tableau
    	            tableModel.setValueAt(nom, selectedRow, 1);
    	            tableModel.setValueAt(prenom, selectedRow, 2);
    	            tableModel.setValueAt(droitAcces, selectedRow, 3);
    	            tableModel.setValueAt(password, selectedRow, 4);
    	            tableModel.setValueAt(numeroTel1, selectedRow, 5);
    	            tableModel.setValueAt(email, selectedRow, 6);
    	            tableModel.setValueAt(dateUtilisateur, selectedRow, 7);
    	            }catch (UtilisateurNotFoundException e) {
                        // Afficher un message d'erreur si l'utilisateur existe déjà
                        JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
    	            // Afficher un message de succès
    	            JOptionPane.showMessageDialog(view, "Utilisateur modifié avec succès !");

    	        } catch (IllegalArgumentException e) {
    	            // Afficher un message d'erreur spécifique
    	            JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	        }
    	    }
    	   model.sauvegarderCSV(); // Sauvegarder les modifications dans le fichier CSV
    	}
    /**
     * Supprime un utilisateur sélectionné dans la table.
     */
    //supprimer
    public void supprimer() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un utilisateur à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Demander une confirmation à l'utilisateur
        int confirmation = JOptionPane.showConfirmDialog(view, 
            "Êtes-vous sûr de vouloir supprimer cet utilisateur ?", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmation != JOptionPane.YES_OPTION) {
            // L'utilisateur a choisi de ne pas supprimer
            return;
        }
        else {
        // Supprimer l'utilisateur sélectionné
        DefaultTableModel tableModel = view.getTableModel();
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        tableModel.removeRow(selectedRow);

        try {
            model.supprimerUtilisateur(id);
        } catch (UtilisateurNotFoundException e) {
            // Afficher un message d'erreur si l'utilisateur existe déjà
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        }
        // Sauvegarder les modifications dans le fichier CSV
        model.sauvegarderCSV();
    }

    /**
     * Recherche un utilisateur dans la table en fonction du nom.
     */
    //rechercher 
    public void rechercher() {
    	String  sh=view.getSearchText();
    	String search = (String) view.getSearchCriteriaCombo().getSelectedItem();
    	if(sh.isEmpty())
    	{
    		 JOptionPane.showMessageDialog(view, "Veuillez saisir un texte", "Erreur", JOptionPane.ERROR_MESSAGE);
    	}
    	else if (search.equals("Nom"))
    	{
    		List<Utilisateur> utilisateursTrouves = model.rechercherParNom(sh);
    		if(utilisateursTrouves!=null && !utilisateursTrouves.isEmpty())
	    		{
	            DefaultTableModel tableModel = view.getTableModel();
	            tableModel.setRowCount(0);
	            for (Utilisateur utilisateur : utilisateursTrouves) 
		            {
		                Object[] row = { utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getDroitAcces(), utilisateur.getPassword() ,utilisateur.getNumerotel(), utilisateur.getEmail(),utilisateur.getDateUtilisateur() };
		                tableModel.addRow(row);
		            }
	    		}
    		else {
    		    //System.out.println("Aucun utilisateur trouvé");
    		    JOptionPane.showMessageDialog(view, "Aucun utilisateur avec ce nom", "Message", JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	else if (search.equals("ID")) {
    	    // Vérifiez si le champ de recherche contient bien un ID valide
    	    try {
    	        int id = Integer.parseInt(sh);  // Convertir le texte en entier
    	        Utilisateur utilisateur = model.rechercherParID(id);  // Rechercher l'utilisateur par ID

    	        if (utilisateur != null) {
    	            DefaultTableModel tableModel = view.getTableModel();
    	            tableModel.setRowCount(0);  // Vider la table avant d'ajouter de nouvelles lignes

    	            // Ajouter l'utilisateur trouvé à la table
    	            Object[] row = { 
    	                utilisateur.getId(), 
    	                utilisateur.getNom(), 
    	                utilisateur.getPrenom(), 
    	                utilisateur.getDroitAcces(), 
    	                utilisateur.getPassword(),
    	                utilisateur.getNumerotel(), 
    	                utilisateur.getEmail(),
    	                utilisateur.getDateUtilisateur() 
    	            };
    	            tableModel.addRow(row);  // Ajouter la ligne dans la table
    	        } else {
    	            // Si aucun utilisateur n'est trouvé pour cet ID
    	            JOptionPane.showMessageDialog(view, "Aucun utilisateur trouvé avec cet ID", "Message", JOptionPane.INFORMATION_MESSAGE);
    	        }
    	    } catch (NumberFormatException e) {
    	        // Si le texte dans le champ de recherche n'est pas un entier valide
    	        JOptionPane.showMessageDialog(view, "Veuillez saisir un ID valide", "Erreur", JOptionPane.ERROR_MESSAGE);
    	    }
    	}
    	
    	else if (search.equals("Date")) {
    	    try {
    	        // Tentative de parsing de la date avec le format spécifié
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	        java.util.Date parsedDate = dateFormat.parse(sh);

    	        // Recherche des utilisateurs en fonction de la date
    	        List<Utilisateur> utilisateursTrouves = model.rechercherParDate(parsedDate);

    	        if (utilisateursTrouves != null && !utilisateursTrouves.isEmpty()) {
    	            // Si des utilisateurs sont trouvés, on met à jour la table avec leurs informations
    	            DefaultTableModel tableModel = view.getTableModel();
    	            tableModel.setRowCount(0); // On vide la table avant d'ajouter les nouvelles lignes

    	            for (Utilisateur utilisateur : utilisateursTrouves) {
    	                // Ajout des utilisateurs trouvés dans la table
    	                Object[] row = {
    	                    utilisateur.getId(),
    	                    utilisateur.getNom(),
    	                    utilisateur.getPrenom(),
    	                    utilisateur.getDroitAcces(),
    	                    utilisateur.getPassword(),
    	                    utilisateur.getNumerotel(),
    	                    utilisateur.getEmail(),
    	                    utilisateur.getDateUtilisateur()
    	                };
    	                tableModel.addRow(row);
    	            }
    	        } else {
    	            // Si aucun utilisateur n'est trouvé, on affiche un message
    	            JOptionPane.showMessageDialog(view, "Aucun utilisateur n'est enregistré à cette date", "Aucun résultat", JOptionPane.INFORMATION_MESSAGE);
    	        }
    	    } catch (ParseException e) {
    	        // Si la chaîne de la date n'est pas correctement formatée
    	        JOptionPane.showMessageDialog(view, "Le format de la date est incorrect. Veuillez entrer une date au format yyyy-MM-dd.", "Erreur ", JOptionPane.ERROR_MESSAGE);
   
    	    }
    	}
    	
    }
    
    
    
}
