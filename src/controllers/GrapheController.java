package controllers;

import views.GlobalView;
import views.GraphePanel;
import models.IEmpruntImpl;

import javax.swing.*;
import java.util.Map;

public class GrapheController {
    private IEmpruntImpl model = new IEmpruntImpl("emprunt.csv");
    private GraphePanel view = new GraphePanel(); // Instance de GraphePanel

    // Constructeur : initialisation avec le modèle et la vue globale
    public GrapheController(GlobalView globalView) {
        // Ajouter l'onglet "Graphe" dans la vue principale
        globalView.getTabbedPane().addTab("Statistiques", view);
        model.lireEmpruntsCSV();  // Lire les données depuis un fichier CSV (ou autre source)

        // Appeler la méthode pour ajouter le graphe
        ajouterOngletGraphe();
    }

    // Méthode pour ajouter l'onglet "Graphe" à la vue principale
    private void ajouterOngletGraphe() {
        // Créer un graphique sous forme de panel et l'afficher dans le tableau des onglets
        JPanel graphePanel = creerGraphePanel();
        // Ajouter l'onglet "Graphe" dans le TabbedPane
        // globalView.getTabbedPane().addTab("Graphe", graphePanel); // Cette ligne est superflue si le graphe est déjà dans view
    }

    // Méthode pour créer un graphe sous forme de JPanel
    private JPanel creerGraphePanel() {
        // Récupérer les données à afficher dans le graphique
        Map<String, Integer> data = model.calculerFrequenceLivres();

        // Mettre à jour le graphique avec les données récupérées
        view.updateGraph(data); // Mise à jour du graphique

        // Retourner la vue (panel avec le graphique mis à jour)
        return view;
    }
}
