package views;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class GlobalView extends JFrame {

    private JTabbedPane tabbedPane = new JTabbedPane();

    public GlobalView() {
        this.setTitle("Bibliothèque");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(700, 500));
        this.setLocationRelativeTo(null);

        // Set a modern Look and Feel
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ajouterComposantes();
        this.setVisible(true); 
    }

    private void ajouterComposantes() {
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }
}
