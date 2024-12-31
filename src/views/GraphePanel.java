package views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;

public class GraphePanel extends JPanel {
    private DefaultPieDataset dataset;

    // Constructeur sans paramètre
    public GraphePanel() {
        this.dataset = new DefaultPieDataset();
        setLayout(new BorderLayout()); // Utilisation de BorderLayout pour mieux gérer les composants

        // Crée un graphique par défaut (vide)
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Livres les plus empruntés",  // Titre
                dataset,                      // Données
                true,                         // Inclure une légende
                true,                         // Afficher les outils
                false                         // Affichage des URL
        );

        // Personnalisation du graphique
        pieChart.setBackgroundPaint(Color.white); // Change le fond du graphique
        pieChart.setBorderPaint(Color.BLACK); // Ajoute une bordure autour du graphique

        // Personnalisation du titre du graphique
        pieChart.getTitle().setPaint(Color.BLACK); // Couleur du titre
        pieChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 22)); // Police et taille du titre

        // Personnalisation de l'axe de légende (police plus grande, plus visible)
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 16)); // Police plus grande pour les labels
        plot.setLabelPaint(Color.BLACK); // Couleur noire pour le texte des labels

        // Personnalisation des étiquettes avec les pourcentages
        DecimalFormat df = new DecimalFormat("#.##"); // Format des pourcentages
        StandardPieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0} : ({2}%)", // Format : Nom : Valeur (Pourcentage)
                df, 
                df
        );
        plot.setLabelGenerator(labelGenerator); // Application du générateur d'étiquettes

        // Ajouter des effets d'ombre pour simuler un effet 3D
        plot.setShadowPaint(Color.GRAY); // Ombre derrière le graphique pour un effet 3D léger
        plot.setLabelBackgroundPaint(Color.WHITE); // Fond blanc derrière les labels
        plot.setLabelShadowPaint(Color.BLACK); // Ombre pour les labels

        // Personnalisation de la bordure des secteurs
        plot.setOutlinePaint(Color.BLACK); // Couleur de la bordure des sections
        plot.setOutlineStroke(new BasicStroke(1.0f)); // Épaisseur de la bordure

        // Ajout d'un panel avec le graphique
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); // Définir une taille fixe
        chartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajout d'une marge autour du graphique

        // Ajout du chartPanel à la vue (panneau principal)
        this.add(chartPanel, BorderLayout.CENTER);
    }

    // Méthode pour mettre à jour le graphique avec de nouvelles données
    public void updateGraph(Map<String, Integer> data) {
        // Mise à jour des données du dataset
        dataset.clear();  // Efface les anciennes données
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Redessine le graphique avec les nouvelles données
        this.repaint();
        this.revalidate(); // Redimensionner et réorganiser les composants si nécessaire
    }
}
