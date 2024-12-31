package controllers;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import exceptions.RetourExisteException;
import models.Emprunt;
import models.IEmpruntImpl;
import models.IRetourImpl;
import models.Retour;
import views.EmpruntsPanel;
import views.GlobalView;
import views.RetoursPanel;

public class RetoursController {
	
	private IRetourImpl model=new IRetourImpl("retours.csv");
    private RetoursPanel view = new RetoursPanel();
    
    
    public RetoursController(GlobalView globalView) {
    	model.lireRetoursCSV();
    	updateTable();

    // Ajouter ce panneau comme onglet dans la vue globale
    globalView.getTabbedPane().addTab("Gestion des retours", view);
    
    
    
    
    
    configureListeners();
    
    }
    
    private void configureListeners() {
    	
    	
    	
        view.getSearchButton().addActionListener(e->rechercherClick());
        view.getDeleteButton().addActionListener(e->supprimerClick());
        view.getEditButton().addActionListener(e->modifierClick());
        

        
        view.getRetourstable().getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = view.getRetourstable().columnAtPoint(e.getPoint());

                // Vérifier si la colonne cliquée est celle de "Date Emprunt"
                if (column == 0) { // 1 correspond à l'index de la colonne "Id"
                	trierParId();
                } 
                else if (column == 1) { // 3 correspond à l'index de la colonne "Date Emprunt"
                    trierParIdEmprunt();
                }
                else if (column == 2) { // 3 correspond à l'index de la colonne "Date Emprunt"
                    trierParDateRetourActuelle();
                }
                else if (column == 3) { // 3 correspond à l'index de la colonne "Date Emprunt"
                    trierParRetard();
                } 
                
                else {
                    JOptionPane.showMessageDialog(view, "Tri non implémenté pour cette colonne.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        
    	
    }
    
    
    
    
    public void supprimerClick(){
    	
    	
        int selectedRow = view.getRetourstable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un retour à supprimer.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Récupérer l'ID de l'emprunt depuis la table
        DefaultTableModel tableModel = (DefaultTableModel) view.getRetourstable().getModel();
        int idEmprunt = (int) tableModel.getValueAt(selectedRow, 0);

        // Supprimer l'emprunt du modèle
        try {
            model.supprimerRetour(idEmprunt);
            // Sauvegarder dans le fichier CSV
            model.sauvegarderRetoursCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Retour supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de la suppression du retour : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    	
    }
    
    
    
    
    
    public void modifierClick() {
    	
	    int selectedRow = view.getRetourstable().getSelectedRow();

	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(view, "Veuillez sélectionner un retour à modifier.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Récupérer les valeurs actuelles depuis la table
	    DefaultTableModel tableModel = (DefaultTableModel) view.getRetourstable().getModel();
	    int id = (int) tableModel.getValueAt(selectedRow, 0);
	    int idEmprunt = (int) tableModel.getValueAt(selectedRow, 1);

	    // Convertir LocalDate en String pour les dates
	    Object dateRetourActuelleObj = tableModel.getValueAt(selectedRow, 2);
	    String dateRetourActuelleStr = dateRetourActuelleObj instanceof LocalDate ? ((LocalDate) dateRetourActuelleObj).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : dateRetourActuelleObj.toString();
	    int retard = (int)tableModel.getValueAt(selectedRow, 3);
	    


	    // Créer des champs pour chaque attribut
	    JTextField idEmpruntField = new JTextField(String.valueOf(idEmprunt));
	    JTextField dateRetourActuelleField = new JTextField(dateRetourActuelleStr);
	    JTextField retardField = new JTextField(String.valueOf(retard));

	    // Organiser les champs dans un JPanel
	    JPanel panel = new JPanel(new GridLayout(3, 2));
	    panel.add(new JLabel("ID Emprunt:"));
	    panel.add(idEmpruntField);
	    panel.add(new JLabel("Date Retour Actuelle (dd/MM/yyyy):"));
	    panel.add(dateRetourActuelleField);
	    panel.add(new JLabel("Retard"));
	    panel.add(retardField);

	    // Afficher la boîte de dialogue
	    int result = JOptionPane.showConfirmDialog(view, panel, "Modifier le retour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	    if (result == JOptionPane.OK_OPTION) {
	        try {
	            // Validation des données
	            int newIdEmprunt = Integer.parseInt(idEmpruntField.getText());
	            LocalDate newDateRetourActuelle = parseDate(dateRetourActuelleField.getText());
	            int newretard = Integer.parseInt(retardField.getText());
	            
	            // Modifier l'emprunt dans le modèle
	            model.modifierRetour(id, newIdEmprunt, newDateRetourActuelle, newretard);

	            // Sauvegarder dans le fichier CSV
	            model.sauvegarderRetoursCSV();

	            // Mettre à jour la table
	            updateTable();
	            
	            JOptionPane.showMessageDialog(view, "Retour modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(view, "Veuillez entrer un ID Emprunt et un Retard valide (entier).", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
	        } catch (DateTimeParseException e) {
	            JOptionPane.showMessageDialog(view, "Veuillez entrer une date valide au format dd/MM/yyyy.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
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
    
    
    
    
    
    
    
    
    
    
    
    
    public void rechercherClick() {
        String searchText = view.getSearchField().getText().toLowerCase(); // Récupère le texte de recherche

        // Filtrer les emprunts en fonction du texte recherché
        List<Retour> filteredRetour = model.getListeRetour().stream()
            .filter(retour -> 
                String.valueOf(retour.getId()).toLowerCase().contains(searchText) ||
                String.valueOf(retour.getIdEmprunt()).toLowerCase().contains(searchText) ||
                retour.getDateRetourActuelle().toString().toLowerCase().contains(searchText) ||
                String.valueOf(retour.getRetard()).toLowerCase().contains(searchText)
            )
            .collect(Collectors.toList());

        // Mettre à jour la table avec les résultats filtrés
        DefaultTableModel tableModel = (DefaultTableModel) view.getRetourstable().getModel();
        tableModel.setRowCount(0); // Vider la table avant d'ajouter les nouvelles lignes

        for (Retour retour : filteredRetour) {
            tableModel.addRow(new Object[] {
                    retour.getId(),
                    retour.getIdEmprunt(),
                    retour.getDateRetourActuelle(),
                    retour.getRetard()
            });
        }
        
        if (filteredRetour.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Aucun retour trouvé pour la recherche : " + searchText, "Aucun résultat", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    
    
    
    public void trierParId() {
            try {
                // Appeler la méthode de tri dans le modèle
                model.trierListeRetourParId();

                // Sauvegarder les changements dans le fichier CSV
                model.sauvegarderRetoursCSV();

                // Mettre à jour la table
                updateTable();

                JOptionPane.showMessageDialog(view, "Table triée par Id.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Erreur lors du tri par Id : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    
    public void trierParIdEmprunt() {
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeRetourParIdEmprunt();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderRetoursCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Id Emprunt.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Id Emprunt : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }   
    
    
    public void trierParDateRetourActuelle() {
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeRetourParDateRetourActuelle();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderRetoursCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Date Retour Actuelle.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Date Retour Actuelle : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }   
    
    
    
    public void trierParRetard() {
        try {
            // Appeler la méthode de tri dans le modèle
            model.trierListeRetoursParRetard();

            // Sauvegarder les changements dans le fichier CSV
            model.sauvegarderRetoursCSV();

            // Mettre à jour la table
            updateTable();

            JOptionPane.showMessageDialog(view, "Table triée par Retard.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par Retard : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void updateTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getRetourstable().getModel();

        // Clear all rows
        tableModel.setRowCount(0);

        // Reload data from the model
        List<Retour> retours = model.getListeRetour();
        for (Retour retour : retours) {
            tableModel.addRow(new Object[]{
                retour.getId(),
                retour.getIdEmprunt(),
                retour.getDateRetourActuelle(),
                retour.getRetard()
            });
        }
    }

    
    
    
}
