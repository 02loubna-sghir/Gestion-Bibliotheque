package controllers;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import exceptions.LivreNotFoundException;
//import exceptions.EmpruntExisteException;
import exceptions.LivreExisteException;
import models.Livre;
import models.Emprunt;
import models.IEmpruntImpl;
import models.ILivreImpl;  // Assuming ILivreImpl is the actual model implementation
import views.LivrePanel;
//import views.EmpruntsPanel;
import views.GlobalView;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


public class LivreController {
    private IEmpruntImpl modelEmp = new IEmpruntImpl("emprunt.csv"); // Use ILivreImpl instead of LivreModel
    private ILivreImpl modelLivre = new ILivreImpl("livres.csv"); // Use ILivreImpl instead of LivreModel
    private LivrePanel view = new LivrePanel();
   // private EmpruntsController empruntController; // Ajoutez une référence à EmpruntController
   // private EmpruntsController empruntController;

//    public LivreController(EmpruntsController empruntController) {
//        this.empruntController = empruntController;
//    }

    


    public LivreController(GlobalView globalView) {
        modelLivre.lireLivresCSV();
        updateTable();

        // Ajouter ce panneau comme onglet dans la vue globale
        globalView.getTabbedPane().addTab("Gestion des livres", view);

        // Configurer les actions sur les boutons
        configureListeners();
    }

    private void configureListeners() {
        // Exemple d'action sur les boutons
        view.getAddButton().addActionListener(e -> ajouterLivre());
        view.getEditButton().addActionListener(e -> modifierLivre());
        view.getDeleteButton().addActionListener(e -> supprimerLivre());
        view.getSearchButton().addActionListener(e -> rechercherLivre());
        view.getEmprunterButton().addActionListener(e -> emprunterLivre());

        
        
     // Ajouter un listener pour le tri sur l'en-tête de la table
        view.getLivresTable().getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = view.getLivresTable().columnAtPoint(e.getPoint());

                // Vérifier la colonne cliquée et appeler la méthode de tri correspondante
                switch (column) {
                    case 0: // Id
                    	trierParIdLivre();
                        break;
                    case 1: // Titre
                    	trierParTitreLivre();
                        break;
                    case 2: // Auteur
                        trierParAuteur();
                        break;
                    case 3: // Année de publication
                        trierParAnneePublication();
                        break;
                    case 4: // Genre
                        trierParGenre();
                        break;
                    case 5: // ISBN
                        trierParIsbn();
                        break;
                    case 6: // Editeur
                        trierParEditeur();
                        break;
                    case 7: // Langue
                        trierParLangue();
                        break;
                    case 8: // Disponibilité
                        trierParDisponibilite();
                        break;
                    default:
                        JOptionPane.showMessageDialog(view, "Tri non implémenté pour cette colonne.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
    }

    private void ajouterLivre() {
        // Créer un formulaire pour ajouter un nouveau livre
        JTextField titreField = new JTextField(20);
        JTextField auteurField = new JTextField(20);
        JTextField anneeField = new JTextField(4);
        JTextField genreField = new JTextField(20);
        JTextField isbnField = new JTextField(13);
        JTextField editeurField = new JTextField(20);
        JTextField langueField = new JTextField(10);
        JTextField disponibiliteField = new JTextField(3);

        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(new JLabel("Titre:"));
        panel.add(titreField);
        panel.add(new JLabel("Auteur:"));
        panel.add(auteurField);
        panel.add(new JLabel("Année de publication:"));
        panel.add(anneeField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Éditeur:"));
        panel.add(editeurField);
        panel.add(new JLabel("Langue:"));
        panel.add(langueField);
        panel.add(new JLabel("Disponibilité:"));
        panel.add(disponibiliteField);

        int result = JOptionPane.showConfirmDialog(view, panel, "Ajouter un livre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Validation des données
                String titre = titreField.getText();
                String auteur = auteurField.getText();
                int anneePublication = Integer.parseInt(anneeField.getText());
                String genre = genreField.getText();
                String isbn = isbnField.getText();
                String editeur = editeurField.getText();
                String langue = langueField.getText();
                int disponibilite = Integer.parseInt(disponibiliteField.getText());

                

                // Créer un nouvel objet Livre
                Livre livre = new Livre(titre,auteur,anneePublication,genre,isbn,editeur,langue,disponibilite);

                // Ajouter le livre au modèle
                modelLivre.ajouterLivre(livre);

                // Sauvegarder dans le fichier CSV
                modelLivre.sauvegarderLivresCSV();

                // Mettre à jour la table
                updateTable();

                JOptionPane.showMessageDialog(view, "Livre ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (LivreExisteException e) {
                JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modifierLivre() {
        int selectedRow = view.getLivresTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un livre à modifier.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) view.getLivresTable().getModel();
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String titre = (String) tableModel.getValueAt(selectedRow, 1);
        String auteur = (String) tableModel.getValueAt(selectedRow, 2);
        int anneePublication = (int) tableModel.getValueAt(selectedRow, 3);
        String genre = (String) tableModel.getValueAt(selectedRow, 4);
        String isbn = (String) tableModel.getValueAt(selectedRow, 5);
        String editeur = (String) tableModel.getValueAt(selectedRow, 6);
        String langue = (String) tableModel.getValueAt(selectedRow, 7);
        int disponibilite = (int) tableModel.getValueAt(selectedRow, 8);

        JTextField titreField = new JTextField(titre);
        JTextField auteurField = new JTextField(auteur);
        JTextField anneeField = new JTextField(String.valueOf(anneePublication));
        JTextField genreField = new JTextField(genre);
        JTextField isbnField = new JTextField(isbn);
        JTextField editeurField = new JTextField(editeur);
        JTextField langueField = new JTextField(langue);
        JTextField disponibiliteField = new JTextField(String.valueOf(disponibilite));

        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(new JLabel("Titre:"));
        panel.add(titreField);
        panel.add(new JLabel("Auteur:"));
        panel.add(auteurField);
        panel.add(new JLabel("Année de publication:"));
        panel.add(anneeField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Éditeur:"));
        panel.add(editeurField);
        panel.add(new JLabel("Langue:"));
        panel.add(langueField);
        panel.add(new JLabel("Disponibilité:"));
        panel.add(disponibiliteField);

        int result = JOptionPane.showConfirmDialog(view, panel, "Modifier le livre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Validation des données
                String newTitre = titreField.getText();
                String newAuteur = auteurField.getText();
                int newAnneePublication = Integer.parseInt(anneeField.getText());
                String newGenre = genreField.getText();
                String newIsbn = isbnField.getText();
                String newEditeur = editeurField.getText();
                String newLangue = langueField.getText();
                int newDisponibilite = Integer.parseInt(disponibiliteField.getText());
                
                // Modifier le livre dans le modèle
                modelLivre.modifierLivre(id,newTitre,newAuteur,newAnneePublication, newGenre,newIsbn,newEditeur,newLangue,newDisponibilite);

                // Sauvegarder dans le fichier CSV
                modelLivre.sauvegarderLivresCSV();

                // Mettre à jour la table
                updateTable();

                JOptionPane.showMessageDialog(view, "Livre modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (LivreNotFoundException e) {
                JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void supprimerLivre() {
        int selectedRow = view.getLivresTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un livre à supprimer.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) view.getLivresTable().getModel();
        int idLivre = (int) tableModel.getValueAt(selectedRow, 0);

        try {
            // Supprimer le livre du modèle
            modelLivre.supprimerLivre(idLivre);
            modelLivre.sauvegarderLivresCSV();
            updateTable();

            JOptionPane.showMessageDialog(view, "Livre supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (LivreNotFoundException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rechercherLivre() {
        String searchQuery = view.getSearchField().getText().toLowerCase();
        List<Livre> livres = modelLivre.recherche(searchQuery);

        // Mettre à jour la table avec les résultats de la recherche
        updateTable(livres);
    }


    private void updateTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getLivresTable().getModel();
        tableModel.setRowCount(0); // Supprimer les anciennes lignes

        List<Livre> livres = modelLivre.getListe();
        for (Livre livre : livres) {
            tableModel.addRow(new Object[]{
                livre.getId(),
                livre.getTitre(),
                livre.getAuteur(),
                livre.getAnneePublication(),
                livre.getGenre(),
                livre.getIsbn(),
                livre.getEditeur(),
                livre.getLangue(),
                livre.getDisponibilite()
            });
        }
    }

    private void updateTable(List<Livre> livres) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getLivresTable().getModel();
        tableModel.setRowCount(0); // Supprimer les anciennes lignes

        for (Livre livre : livres) {
            tableModel.addRow(new Object[]{
                livre.getId(),
                livre.getTitre(),
                livre.getAuteur(),
                livre.getAnneePublication(),
                livre.getGenre(),
                livre.getIsbn(),
                livre.getEditeur(),
                livre.getLangue(),
                livre.getDisponibilite()
            });
        }
    }
    
    public void trierParIdLivre() {
        try {
            modelLivre.trierLivresParId();
            modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par ID.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par ID : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParTitreLivre() {
        try {
            modelLivre.trierLivresParTitre();
            modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par titre.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par titre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParAuteur() {
        try {
            modelLivre.trierLivresParAuteur();
            modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par auteur.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par auteur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParAnneePublication() {
        try {
            modelLivre.trierLivresParAnneePublication();
            modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par année de publication.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par année de publication : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParGenre() {
        try {
        	modelLivre.trierLivresParGenre();
        	modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par genre.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par genre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParIsbn() {
        try {
        	modelLivre.trierLivresParIsbn();
        	modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par ISBN.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par ISBN : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParEditeur() {
        try {
        	modelLivre.trierLivresParEditeur();
        	modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par éditeur.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par éditeur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParLangue() {
        try {
        	modelLivre.trierLivresParLangue();
        	modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par langue.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par langue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void trierParDisponibilite() {
        try {
        	modelLivre.trierLivresParDisponibilite();
        	modelLivre.sauvegarderLivresCSV();
            updateTable();
            JOptionPane.showMessageDialog(view, "Table triée par disponibilité.", "Tri effectué", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors du tri par disponibilité : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
//    private void emprunterLivre() {
//        int selectedRow = view.getLivresTable().getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un livre à emprunter.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        DefaultTableModel tableModel = (DefaultTableModel) view.getLivresTable().getModel();
//        int idLivre = (int) tableModel.getValueAt(selectedRow, 0);
//        String titreLivre = (String) tableModel.getValueAt(selectedRow, 1);
//        int disponibilite = (int) tableModel.getValueAt(selectedRow, 8);
//
//        if (disponibilite <= 0) {
//            JOptionPane.showMessageDialog(view, "Ce livre n'est pas disponible pour l'emprunt.", "Non disponible", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        // Créer le formulaire d'emprunt
//        JTextField idUtilisateurField = new JTextField(10);
//        
//        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
//        panel.add(new JLabel("ID Livre:"));
//        panel.add(new JLabel(String.valueOf(idLivre)));
//        panel.add(new JLabel("Titre:"));
//        panel.add(new JLabel(titreLivre));
//        panel.add(new JLabel("ID Utilisateur:"));
//        panel.add(idUtilisateurField);
//
//        int result = JOptionPane.showConfirmDialog(view, panel, 
//                "Nouvel emprunt", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            try {
//                int idUtilisateur = Integer.parseInt(idUtilisateurField.getText());
//                LocalDate dateEmprunt = LocalDate.now();
//                LocalDate dateRetour = dateEmprunt.plusDays(14); // Période de prêt de 14 jours
//
//                // Créer le nouvel emprunt
//                Emprunt emprunt = new Emprunt(idUtilisateur, titreLivre, dateEmprunt, dateRetour);
//
//                // Ajouter l'emprunt
//                model.getEmpruntModel().ajouterEmprunt(emprunt);
//                model.getEmpruntModel().sauvegarderEmpruntsCSV();
//
//                // Mettre à jour la disponibilité du livre
//                model.modifierLivre(idLivre, titreLivre, 
//                                  (String)tableModel.getValueAt(selectedRow, 2), // auteur
//                                  (int)tableModel.getValueAt(selectedRow, 3),    // anneePublication 
//                                  (String)tableModel.getValueAt(selectedRow, 4), // genre
//                                  (String)tableModel.getValueAt(selectedRow, 5), // isbn
//                                  (String)tableModel.getValueAt(selectedRow, 6), // editeur
//                                  (String)tableModel.getValueAt(selectedRow, 7), // langue
//                                  disponibilite - 1);                            // nouvelle disponibilité
//                
//                model.sauvegarderLivresCSV();
//                updateTable();
//
//                JOptionPane.showMessageDialog(view, "Emprunt enregistré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(view, "L'ID utilisateur doit être un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
//            } catch (EmpruntExisteException ex) {
//                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(view, "Erreur lors de l'enregistrement de l'emprunt: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
    
    
    
    
    private void emprunterLivre() {
        int selectedRow = view.getLivresTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un livre à emprunter.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) view.getLivresTable().getModel();
        int idLivre = (int) tableModel.getValueAt(selectedRow, 0);
        String titreLivre = (String) tableModel.getValueAt(selectedRow, 1);
        int disponibilite = (int) tableModel.getValueAt(selectedRow, 8);

        if (disponibilite <= 1) {
            JOptionPane.showMessageDialog(view, "Ce livre n'est pas disponible pour l'emprunt.", "Non disponible", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Créer le formulaire d'emprunt
        JTextField idUtilisateurField = new JTextField(10);
        JTextField dateEmpruntField = new JTextField(10);
        JTextField dateRetourField = new JTextField(10);
        dateRetourField.setEditable(false); // Rendre ce champ non modifiable

        // Ajouter un DocumentListener pour générer la date de retour automatiquement
        dateEmpruntField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateDateRetour() {
                try {
                    String dateEmpruntText = dateEmpruntField.getText();
                    if (!dateEmpruntText.isEmpty()) {
                        LocalDate dateEmprunt = LocalDate.parse(dateEmpruntText);
                        LocalDate dateRetour = dateEmprunt.plusDays(15); // Ajouter 15 jours
                        dateRetourField.setText(dateRetour.toString());
                    }
                } catch (DateTimeParseException e) {
                    dateRetourField.setText(""); // Efface la date de retour si la date d'emprunt est invalide
                }
            }

            
            public void insertUpdate(DocumentEvent e) {
                updateDateRetour();
            }

            
            public void removeUpdate(DocumentEvent e) {
                updateDateRetour();
            }

            
            public void changedUpdate(DocumentEvent e) {
                updateDateRetour();
            }
        });

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("ID Livre:"));
        panel.add(new JLabel(String.valueOf(idLivre)));
        panel.add(new JLabel("Titre:"));
        panel.add(new JLabel(titreLivre));
        panel.add(new JLabel("ID Utilisateur:"));
        panel.add(idUtilisateurField);
        panel.add(new JLabel("Date Emprunt (yyyy-MM-dd):"));
        panel.add(dateEmpruntField);
        panel.add(new JLabel("Date Retour "));
        panel.add(dateRetourField);

        int result = JOptionPane.showConfirmDialog(view, panel,
                "Nouvel emprunt", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int idUtilisateur = Integer.parseInt(idUtilisateurField.getText());
                LocalDate dateEmprunt = LocalDate.parse(dateEmpruntField.getText());
                LocalDate dateRetour = LocalDate.parse(dateRetourField.getText());

                // Créer le nouvel emprunt
                Emprunt emprunt = new Emprunt(idUtilisateur, titreLivre, dateEmprunt, dateRetour);

                // Ajouter l'emprunt
                if (modelEmp instanceof IEmpruntImpl) {
                    modelEmp.viderListe();
                    modelEmp.lireEmpruntsCSV();
                    ((IEmpruntImpl) modelEmp).ajouterEmprunt(emprunt);
                    modelEmp.sauvegarderEmpruntsCSV();
                } else {
                    JOptionPane.showMessageDialog(view, "Le modèle ne prend pas en charge les emprunts.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mettre à jour la disponibilité du livre
                modelLivre.modifierLivre(idLivre, titreLivre,
                        (String) tableModel.getValueAt(selectedRow, 2), // auteur
                        (int) tableModel.getValueAt(selectedRow, 3),    // anneePublication
                        (String) tableModel.getValueAt(selectedRow, 4), // genre
                        (String) tableModel.getValueAt(selectedRow, 5), // isbn
                        (String) tableModel.getValueAt(selectedRow, 6), // editeur
                        (String) tableModel.getValueAt(selectedRow, 7), // langue
                        disponibilite - 1);                            // nouvelle disponibilité

                modelLivre.sauvegarderLivresCSV();
                updateTable();

                JOptionPane.showMessageDialog(view, "Emprunt enregistré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "L'ID utilisateur doit être un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(view, "Le format des dates est incorrect. Utilisez le format yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'enregistrement de l'emprunt: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

        
}
