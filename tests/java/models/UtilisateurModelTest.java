package models;

import models.Utilisateur;
import models.UtilisateurModel;
import exceptions.UtilisateurExsiste;
import exceptions.UtilisateurNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class UtilisateurModelTest {

    private UtilisateurModel utilisateurModel;
    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        // Initialisation de la classe UtilisateurModel avant chaque test
        utilisateurModel = new UtilisateurModel();
        utilisateur = new Utilisateur("John", "Doe", "admin", "password123", 1234567890, "john.doe@example.com", LocalDate.of(2023, 5, 1));
    }

    @Test
    public void testAjouterUtilisateur() throws UtilisateurExsiste {
        // Vérifier que l'utilisateur peut être ajouté
        utilisateurModel.ajouterUtilisateur(utilisateur);

        // Vérifier que la taille de la liste a augmenté
        assertEquals(1, utilisateurModel.getListe().size());

        // Vérifier si l'utilisateur a bien été ajouté en vérifiant son ID
        assertEquals(utilisateur.getId(), utilisateurModel.getListe().get(0).getId());
    }

    @Test
    public void testAjouterUtilisateurUtilisateurExistant() {
        // Ajouter un utilisateur
        try {
            utilisateurModel.ajouterUtilisateur(utilisateur);
        } catch (UtilisateurExsiste e) {
            fail("L'exception UtilisateurExsiste ne devrait pas être lancée");
        }

        // Essayer d'ajouter un utilisateur avec les mêmes données, cela devrait échouer
        Utilisateur utilisateurExistant = new Utilisateur("John", "Doe", "admin", "password123", 1234567890, "john.doe@example.com", LocalDate.of(2023, 5, 1));
        
        assertThrows(UtilisateurExsiste.class, () -> {
            utilisateurModel.ajouterUtilisateur(utilisateurExistant);
        });
    }

    @Test
    public void testRechercherParID() throws UtilisateurExsiste {
        // Ajouter un utilisateur
        utilisateurModel.ajouterUtilisateur(utilisateur);

        // Vérifier que l'utilisateur peut être retrouvé par son ID
        Utilisateur utilisateurTrouve = utilisateurModel.rechercherParID(utilisateur.getId());
        assertNotNull(utilisateurTrouve);
        assertEquals(utilisateur.getId(), utilisateurTrouve.getId());
    }

    @Test
    public void testRechercherParIDUtilisateurNonTrouve() {
        // Essayer de chercher un utilisateur avec un ID qui n'existe pas
        assertNull(utilisateurModel.rechercherParID(999));
    }

    @Test
    public void testSupprimerUtilisateur() throws UtilisateurExsiste, UtilisateurNotFoundException {
        // Ajouter un utilisateur
        utilisateurModel.ajouterUtilisateur(utilisateur);

        // Vérifier que la taille de la liste est 1
        assertEquals(1, utilisateurModel.getListe().size());

        // Supprimer l'utilisateur
        utilisateurModel.supprimerUtilisateur(utilisateur.getId());

        // Vérifier que la taille de la liste est 0 après suppression
        assertEquals(0, utilisateurModel.getListe().size());
    }

    @Test
    public void testSupprimerUtilisateurNonTrouve() {
        // Essayer de supprimer un utilisateur qui n'existe pas
        assertThrows(UtilisateurNotFoundException.class, () -> {
            utilisateurModel.supprimerUtilisateur(999);
        });
    }

    @Test
    public void testTrierListeUtilisateurs() throws UtilisateurExsiste {
        // Ajouter des utilisateurs
        Utilisateur utilisateur1 = new Utilisateur("Alice", "Smith", "user", "password456", 987654321, "alice.smith@example.com", LocalDate.of(2023, 6, 1));
        Utilisateur utilisateur2 = new Utilisateur("Bob", "Johnson", "admin", "password789", 555555555, "bob.johnson@example.com", LocalDate.of(2022, 7, 1));
        utilisateurModel.ajouterUtilisateur(utilisateur);
        utilisateurModel.ajouterUtilisateur(utilisateur1);
        utilisateurModel.ajouterUtilisateur(utilisateur2);

        // Trier par nom
        utilisateurModel.trierParNom();

        // Vérifier l'ordre des utilisateurs
        List<Utilisateur> listeUtilisateurs = utilisateurModel.getListe();
        assertEquals("Alice", listeUtilisateurs.get(0).getNom());
        assertEquals("Bob", listeUtilisateurs.get(1).getNom());
        assertEquals("John", listeUtilisateurs.get(2).getNom());
    }
}
