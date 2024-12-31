package controllers;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import exceptions.LivreNotFoundException;
import views.GlobalView;

public class MainController {

    private GlobalView globalView;

    public MainController() throws LivreNotFoundException {
        // Initialiser la vue globale
        this.globalView = new GlobalView();

        // Initialiser et connecter les contrôleurs spécifiques
        initializeControllers();

        // Ajouter un écouteur d'événements pour gérer la fermeture de la fenêtre
        globalView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Demander confirmation à l'utilisateur avant de fermer la fenêtre
                int choix = JOptionPane.showConfirmDialog(globalView, "Êtes-vous sûr de vouloir quitter ?");
                if (choix == JOptionPane.YES_OPTION) {
                    globalView.dispose(); // Fermer la fenêtre
                }
            }
        });
    }

    private void initializeControllers() throws LivreNotFoundException {
        
         new UtilisateurController(globalView);
         new LivreController(globalView);
         new EmpruntsController(globalView);
         new RetoursController(globalView);
         new GrapheController(globalView);
    }
}
