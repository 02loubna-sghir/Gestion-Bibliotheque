package model;

import exceptions.LivreExisteException;
import exceptions.LivreNotFoundException;
import models.ILivreImpl;
import models.Livre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class LivreModel {

    private ILivreImpl iLivre;

    @BeforeEach
    void setUp() {
        iLivre = new ILivreImpl();
    }

    @Test
    void testAjouterLivre() throws LivreExisteException {
        Livre livre = new Livre("Titre1", "Auteur1", 2021, "Genre1", "ISBN1", "Editeur1", "Langue1", 5);
        iLivre.ajouterLivre(livre);
        
        assertEquals(1, iLivre.getListe().size());
        assertTrue(iLivre.getListe().contains(livre));
    }

    @Test
    void testAjouterLivre_Duplication() {
        Livre livre = new Livre("Titre1", "Auteur1", 2021, "Genre1", "ISBN1", "Editeur1", "Langue1", 5);
        assertDoesNotThrow(() -> iLivre.ajouterLivre(livre));
        
        assertThrows(LivreExisteException.class, () -> iLivre.ajouterLivre(livre));
    }

    @Test
    void testSupprimerLivre() throws LivreExisteException, LivreNotFoundException {
        Livre livre = new Livre("Titre1", "Auteur1", 2021, "Genre1", "ISBN1", "Editeur1", "Langue1", 5);
        iLivre.ajouterLivre(livre);

        iLivre.supprimerLivre(livre.getId());
        assertFalse(iLivre.getListe().contains(livre));
    }

    @Test
    void testSupprimerLivre_Inexistant() {
        assertThrows(LivreNotFoundException.class, () -> iLivre.supprimerLivre(999));
    }

//    @Test
//    void testModifierLivre() throws LivreExisteException, LivreNotFoundException {
//        Livre livre = new Livre("Titre1", "Auteur1", 2021, "Genre1", "ISBN1", "Editeur1", "Langue1", 5);
//        iLivre.ajouterLivre(livre);
//
//        iLivre.modifierLivre(livre.getId(), "TitreModifie", "AuteurModifie", 2022, "GenreModifie", "ISBNModifie", "EditeurModifie", "LangueModifie", 10);
//        
//        Livre modifie = iLivre.rechercherParId(livre.getId());
//        assertEquals("TitreModifie", modifie.getTitre());
//        assertEquals(2022, modifie.getAnneePublication());
//    }

    @Test
    void testRechercherParId() throws LivreExisteException {
        Livre livre = new Livre("Titre1", "Auteur1", 2021, "Genre1", "ISBN1", "Editeur1", "Langue1", 5);
        iLivre.ajouterLivre(livre);

        Livre result = iLivre.rechercherParId(livre.getId());
        assertNotNull(result);
        assertEquals(livre, result);
    }

    @Test
    void testListerLivres() throws LivreExisteException {
        Livre livre1 = new Livre("Titre1", "Auteur1", 2021, "Genre1", "ISBN1", "Editeur1", "Langue1", 5);
        Livre livre2 = new Livre("Titre2", "Auteur2", 2020, "Genre2", "ISBN2", "Editeur2", "Langue2", 10);
        iLivre.ajouterLivre(livre1);
        iLivre.ajouterLivre(livre2);

        List<Livre> liste = iLivre.getListe();
        assertEquals(2, liste.size());
        assertTrue(liste.contains(livre1));
        assertTrue(liste.contains(livre2));
    }

    @Test
    void testRecherche() throws LivreExisteException {
        Livre livre1 = new Livre("Titre1", "Auteur1", 2021, "Genre1", "ISBN1", "Editeur1", "Langue1", 5);
        Livre livre2 = new Livre("Titre2", "Auteur2", 2020, "Genre2", "ISBN2", "Editeur2", "Langue2", 10);
        iLivre.ajouterLivre(livre1);
        iLivre.ajouterLivre(livre2);

        List<Livre> result = iLivre.recherche("Titre1");
        assertEquals(1, result.size());
        assertTrue(result.contains(livre1));
    }
}
