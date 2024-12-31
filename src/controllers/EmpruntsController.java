package controllers;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;



import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import exceptions.DateInacceptableException;
import exceptions.RetourExisteException;
import models.Emprunt;
import models.IEmpruntImpl;
import models.IRetourImpl;
import models.Retour;
import views.EmpruntsPanel;
import views.GlobalView;
import views.RetoursPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class EmpruntsController {
	
	private IEmpruntImpl model=new IEmpruntImpl("emprunt.csv");
    private EmpruntsPanel view = new EmpruntsPanel();
    private IRetourImpl retourmodel = new IRetourImpl("retours.csv");
    private RetoursPanel retourview = new RetoursPanel();

    public EmpruntsController(GlobalView globalView) {
         
        model.lireEmpruntsCSV();
        updateTable();

        // Ajouter ce panneau comme onglet dans la vue globale
        globalView.getTabbedPane().addTab("Gestion des emprunts", view);

        // Configurer les actions sur les boutons
        configureListeners();
    }

    private void configureListeners() {
    	
    	
        // Exemple d'action sur le bouton "modifier"
        view.getDeleteButton().addActionListener(e -> supprimerClick());
        view.getEditButton().addActionListener(e-> modifierClick());
        view.getProlongButton().addActionListener(e-> prolongerClick());
        view.getSearchButton().addActionListener(e->rechercherClick());
        view.getReturnButton().addActionListener(e->retournerClick());
        view.getEmpruntstable().getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = view.getEmpruntstable().columnAtPoint(e.getPoint());

                // Vérifier si la colonne cliquée est celle de "Date Emprunt"
                if (column == 0) { // 1 correspond à l'index de la colonne "Id"
                	trierParId();
                } 
                else if (column == 1) { // 3 correspond à l'index de la colonne "Date Emprunt"
                    trierParIdUtilisateur();
                }
                else if (column == 2) { // 3 correspond à l'index de la colonne "Date Emprunt"
                    trierParTitreLivre();
                }
                else if (column == 3) { // 3 correspond à l'index de la colonne "Date Emprunt"
                    trierParDateEmprunt();
                } 
                else if (column == 4) { // 3 correspond à l'index de la colonne "Date Emprunt"
                    trierParDateRetour();
                } 
                
                else {
                    JOptionPane.showMessageDialog(view, "Tri non implémenté pour cette colonne.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void supprimerClick() {
        //JOptionPane.showMessageDialog(view, "Hello world depuis le contrôleur !");
    	
    	
    	
    	
        int selectedRow = view.getEmpruntstable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un emprunt à supprimer.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Récupérer l'ID de l'emprunt depuis la table
        DefaultTableModel tableModel = (DefaultTableModel) view.getEmpruntstable().getModel();
        int idEmprunt = (int) tableModel.getValueAt(selectedRow, 0);

        // Supprimer l'emprunt du modèle
        try {
            model.supprimerEmprunt(idEmprunt);
            // Sauvegarder dans le fichier CSV
            model.sauvegarderEmpruntsCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Emprunt supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de la suppression de l'emprunt : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    	
    	
    	
    	
    }
    
    
    
    
    
    
    public void modifierClick() {
    	    int selectedRow = view.getEmpruntstable().getSelectedRow();

    	    if (selectedRow == -1) {
    	        JOptionPane.showMessageDialog(view, "Veuillez sélectionner un emprunt à modifier.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
    	        return;
    	    }

    	    // Récupérer les valeurs actuelles depuis la table
    	    DefaultTableModel tableModel = (DefaultTableModel) view.getEmpruntstable().getModel();
    	    int id = (int) tableModel.getValueAt(selectedRow, 0);
    	    int idUtilisateur = (int) tableModel.getValueAt(selectedRow, 1);
    	    String titreLivre = (String) tableModel.getValueAt(selectedRow, 2);
    	    // Convertir LocalDate en String pour les dates
    	    Object dateEmpruntObj = tableModel.getValueAt(selectedRow, 3);
    	    Object dateRetourObj = tableModel.getValueAt(selectedRow, 4);
    	    String dateEmpruntStr = dateEmpruntObj instanceof LocalDate ? ((LocalDate) dateEmpruntObj).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : dateEmpruntObj.toString();
    	    String dateRetourStr = dateRetourObj instanceof LocalDate ? ((LocalDate) dateRetourObj).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : dateRetourObj.toString();


    	    // Créer des champs pour chaque attribut
    	    JTextField idUtilisateurField = new JTextField(String.valueOf(idUtilisateur));
    	    JTextField titreLivreField = new JTextField(titreLivre);
    	    JTextField dateEmpruntField = new JTextField(dateEmpruntStr);
    	    JTextField dateRetourField = new JTextField(dateRetourStr);

    	    // Organiser les champs dans un JPanel
    	    JPanel panel = new JPanel(new GridLayout(4, 2));
    	    panel.add(new JLabel("ID Utilisateur:"));
    	    panel.add(idUtilisateurField);
    	    panel.add(new JLabel("Titre Livre:"));
    	    panel.add(titreLivreField);
    	    panel.add(new JLabel("Date Emprunt (dd/MM/yyyy):"));
    	    panel.add(dateEmpruntField);
    	    panel.add(new JLabel("Date Retour (dd/MM/yyyy):"));
    	    panel.add(dateRetourField);

    	    // Afficher la boîte de dialogue
    	    int result = JOptionPane.showConfirmDialog(view, panel, "Modifier l'emprunt", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    	    if (result == JOptionPane.OK_OPTION) {
    	        try {
    	            // Validation des données
    	            int newIdUtilisateur = Integer.parseInt(idUtilisateurField.getText());
    	            String newTitreLivre = titreLivreField.getText();
    	            LocalDate newDateEmprunt = parseDate(dateEmpruntField.getText());
    	            LocalDate newDateRetour = parseDate(dateRetourField.getText());
    	            
    	            
    	            if(newDateEmprunt.isAfter(newDateRetour)){
    	          	JOptionPane.showMessageDialog(view, "La date de retour saisi est antérieur a la date d'emprunt");
    	          	return;
    	            }
    	            
    	            else {

    	            // Modifier l'emprunt dans le modèle
    	            model.modifierEmprunt(id, newIdUtilisateur, newTitreLivre, newDateEmprunt, newDateRetour);

    	            // Sauvegarder dans le fichier CSV
    	            model.sauvegarderEmpruntsCSV();

    	            // Mettre à jour la table
    	            updateTable();
    	            }
    	            JOptionPane.showMessageDialog(view, "Emprunt modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    	        } catch (NumberFormatException e) {
    	            JOptionPane.showMessageDialog(view, "Veuillez entrer un ID utilisateur valide (entier).", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    	        } catch (DateTimeParseException e) {
    	            JOptionPane.showMessageDialog(view, "Veuillez entrer des dates valides au format dd/MM/yyyy.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    	        } catch (Exception e) {
    	            JOptionPane.showMessageDialog(view, "Erreur lors de la modification de l'emprunt : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	        }
    	    }	
    	
    }
    
    
 // Méthode utilitaire pour parser une date au format dd/MM/yyyy
    public LocalDate parseDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }
    
    
    
    public void prolongerClick() {
	    int selectedRow = view.getEmpruntstable().getSelectedRow();

	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(view, "Veuillez sélectionner un emprunt à prolonger.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    try {
	        // Récupérer les données de l'emprunt sélectionné
	        DefaultTableModel tableModel = (DefaultTableModel) view.getEmpruntstable().getModel();
	        int id = (int) tableModel.getValueAt(selectedRow, 0); // ID de l'emprunt
	        Emprunt emprunt = model.rechercherParId(id);
	        
	        if (emprunt == null) {
	            JOptionPane.showMessageDialog(view, "Emprunt introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    
    	String nouvelleDateStr= JOptionPane.showInputDialog(view,"Entrez nouvelle date d'emprunt (dd/MM/yyyy)");
    	
        if (nouvelleDateStr == null || nouvelleDateStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Aucune date saisie. Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    	
    	
    	LocalDate nouvelleDate=parseDate(nouvelleDateStr);
    	
    	model.prolongerDateRetour(emprunt, nouvelleDate);
    	model.sauvegarderEmpruntsCSV();
    	updateTable();
    	
        JOptionPane.showMessageDialog(view, "Date de retour prolongée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

    } catch (DateInacceptableException ex) {
        JOptionPane.showMessageDialog(view, "Erreur : " + ex.getMessage(), "Date inacceptable", JOptionPane.ERROR_MESSAGE);
    } catch (DateTimeParseException ex) {
        JOptionPane.showMessageDialog(view, "Veuillez entrer une date valide au format dd/MM/yyyy.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(view, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
	    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void trierParDateEmprunt() {
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeEmpruntsParDateEmprunt();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderEmpruntsCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par date d'emprunt.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par date d'emprunt : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    
    
    
    
    
    public void trierParDateRetour() {
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeEmpruntsParDateRetour();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderEmpruntsCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par date de retour.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par date de retour : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    
    
    
    
    public void trierParId() {
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeEmpruntsParId();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderEmpruntsCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Id.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Id : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    
    
    
    public void trierParIdUtilisateur() {	
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeEmpruntsParIdUtilisateur();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderEmpruntsCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Id Utlisateur.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Id Utilisateur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
 
    	
    }
    
    
    
    
    
    public void trierParTitreLivre(){
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeEmpruntsParTitreLivre();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderEmpruntsCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par titre du livre.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par titre du livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    public void rechercherClick() {
        String searchText = view.getSearchField().getText().toLowerCase(); // Récupère le texte de recherche

        // Filtrer les emprunts en fonction du texte recherché
        List<Emprunt> filteredEmprunts = model.getListe().stream()
            .filter(emprunt -> 
                String.valueOf(emprunt.getId()).toLowerCase().contains(searchText) ||
                String.valueOf(emprunt.getIdUtilisateur()).toLowerCase().contains(searchText) ||
                emprunt.getTitreLivre().toLowerCase().contains(searchText) ||
                emprunt.getDateEmprunt().toString().toLowerCase().contains(searchText) ||
                emprunt.getDateRetour().toString().toLowerCase().contains(searchText)
            )
            .collect(Collectors.toList());

        // Mettre à jour la table avec les résultats filtrés
        DefaultTableModel tableModel = (DefaultTableModel) view.getEmpruntstable().getModel();
        tableModel.setRowCount(0); // Vider la table avant d'ajouter les nouvelles lignes

        for (Emprunt emprunt : filteredEmprunts) {
            tableModel.addRow(new Object[] {
                emprunt.getId(),
                emprunt.getIdUtilisateur(),
                emprunt.getTitreLivre(),
                emprunt.getDateEmprunt(),
                emprunt.getDateRetour()
            });
        }
        
        if (filteredEmprunts.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Aucun emprunt trouvé pour la recherche : " + searchText, "Aucun résultat", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    
    
    
   
    
    
    public void retournerClick() {

    	    int selectedRow = view.getEmpruntstable().getSelectedRow();

    	    if (selectedRow == -1) {
    	        JOptionPane.showMessageDialog(view, "Veuillez sélectionner un emprunt.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
    	        return;
    	    }

    	    try {
    	        // Retrieve the selected Emprunt
    	        DefaultTableModel tableModel = (DefaultTableModel) view.getEmpruntstable().getModel();
    	        int idEmprunt = (int) tableModel.getValueAt(selectedRow, 0);
    	        Emprunt emprunt = model.rechercherParId(idEmprunt);

    	        if (emprunt == null) {
    	            JOptionPane.showMessageDialog(view, "Emprunt introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	            return;
    	        }

    	        // Prompt user for dateRetourActuelle
    	        String dateRetourActuelleStr = JOptionPane.showInputDialog(view, "Entrez la date de retour actuelle (dd/MM/yyyy):");
    	        if (dateRetourActuelleStr == null || dateRetourActuelleStr.trim().isEmpty()) {
    	            JOptionPane.showMessageDialog(view, "Aucune date saisie. Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
    	            return;
    	        }

    	        LocalDate dateRetourActuelle = parseDate(dateRetourActuelleStr);

    	        // Calculate retard (difference in days)
    	        LocalDate dateRetour = emprunt.getDateRetour();
    	        int retard = (int) java.time.temporal.ChronoUnit.DAYS.between(dateRetour, dateRetourActuelle);
    	        
    	        if(retard<0) {
    	        	retard = 0;
    	        }

    	        // Create a new Retour
    	        Retour newRetour = new Retour();
    	        //newRetour.setId(retourmodel.getListeRetour().size() + 1); // Auto-generate ID
    	        newRetour.setIdEmprunt(idEmprunt);
    	        newRetour.setDateRetourActuelle(dateRetourActuelle);
    	        newRetour.setRetard(retard);

    	        // Add the Retour to the model
    	        retourmodel.viderListe();
    	        retourmodel.lireRetoursCSV();
    	        retourmodel.ajouterRetour(newRetour);
    	        retourmodel.sauvegarderRetoursCSV();// Save to file
    	        

    	        DefaultTableModel retourstableModel = (DefaultTableModel) retourview.getRetourstable().getModel();
    	        
    	        System.out.println(retourstableModel);
    	        
    	        // Supprimer les lignes existantes
    	        retourstableModel.setRowCount(0);

    	        // Ajouter les données depuis le modèle
    	        List<Retour> retours = retourmodel.getListeRetour();
    	        for (Retour retour : retours) {
    	            retourstableModel.addRow(new Object[]{
    	                retour.getId(),
    	                retour.getIdEmprunt(),
    	                retour.getDateRetourActuelle(),
    	                retour.getRetard()
    	            });
    	            
    	            
    	        }
    	        
    	        model.supprimerEmprunt(idEmprunt);
    	        model.sauvegarderEmpruntsCSV();
    	        updateTable();
    	 
    	        


    	        JOptionPane.showMessageDialog(view, "Retour ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    	    } catch (RetourExisteException ex) {
    	        JOptionPane.showMessageDialog(view, "Le retour existe déjà : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	    } catch (DateTimeParseException ex) {
    	        JOptionPane.showMessageDialog(view, "Veuillez entrer une date valide au format dd/MM/yyyy.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(view, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	    }
    	
    	
    	
    	
    	
    	
    	
    }
    
    
    public void updateTable() {
    	
    	
        DefaultTableModel tableModel = (DefaultTableModel) view.getEmpruntstable().getModel();

        // Supprimer les lignes existantes
        tableModel.setRowCount(0);

        // Ajouter les données depuis le modèle
        List<Emprunt> emprunts = model.getListe();
        for (Emprunt emprunt : emprunts) {
            tableModel.addRow(new Object[]{
                emprunt.getId(),
                emprunt.getIdUtilisateur(),
                emprunt.getTitreLivre(),
                emprunt.getDateEmprunt(),
                emprunt.getDateRetour()
            });
        }
    	
    	
    }
    


	

}









/*public class EmpruntsController {

	private IEmpruntImpl model=new IEmpruntImpl("etudiants.csv");
	private EmpruntsPanel view=new EmpruntsPanel();
	
	
	public EmpruntsController() {
		view.getEditButton().addActionListener(
				e->ajouterClick());
	}
	
	
	public void ajouterClick(){
		JOptionPane.showMessageDialog(view, "hello world");
	}
}
*/

