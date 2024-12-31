package model;

import exceptions.*;
import models.Emprunt;
import models.IEmpruntImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IEmpruntImplTest {

    private IEmpruntImpl empruntService;

    @BeforeEach
    void setUp() {
        empruntService = new IEmpruntImpl();
    }

    @Test
    void testAjouterEmpruntSuccess() throws EmpruntExisteException {
        Emprunt emprunt = new Emprunt(1, "Book Title", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);
        List<Emprunt> emprunts = empruntService.getListe();
        assertTrue(emprunts.contains(emprunt), "Emprunt should be added to the list.");
    }

    @Test
    void testAjouterEmpruntThrowsExceptionForDuplicate() {
        Emprunt emprunt = new Emprunt(1, "Book Title", LocalDate.now(), LocalDate.now().plusDays(7));
        assertDoesNotThrow(() -> empruntService.ajouterEmprunt(emprunt));
        assertThrows(EmpruntExisteException.class, () -> empruntService.ajouterEmprunt(emprunt),
                "Adding the same emprunt should throw EmpruntExisteException.");
    }

    @Test
    void testSupprimerEmpruntSuccess() throws EmpruntExisteException, EmpruntNotFoundException {
        Emprunt emprunt = new Emprunt(1, "Book Title", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);
        empruntService.supprimerEmprunt(emprunt.getId());
        assertFalse(empruntService.getListe().contains(emprunt), "Emprunt should be removed from the list.");
    }

    @Test
    void testSupprimerEmpruntThrowsExceptionForInvalidId() {
        assertThrows(EmpruntNotFoundException.class, () -> empruntService.supprimerEmprunt(999),
                "Removing an emprunt with a non-existent ID should throw EmpruntNotFoundException.");
    }

    @Test
    void testModifierEmpruntSuccess() throws EmpruntExisteException, EmpruntNotFoundException {
        Emprunt emprunt = new Emprunt(1, "Book Title", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        LocalDate newDateRetour = LocalDate.now().plusDays(10);
        empruntService.modifierEmprunt(emprunt.getId(), 2, "Updated Title", LocalDate.now(), newDateRetour);

        Emprunt updatedEmprunt = empruntService.rechercherParId(emprunt.getId());
        assertEquals("Updated Title", updatedEmprunt.getTitreLivre(), "Title should be updated.");
        assertEquals(newDateRetour, updatedEmprunt.getDateRetour(), "Return date should be updated.");
    }

    @Test
    void testModifierEmpruntThrowsExceptionForInvalidId() {
        assertThrows(EmpruntNotFoundException.class, () ->
                empruntService.modifierEmprunt(999, 2, "Updated Title", LocalDate.now(), LocalDate.now().plusDays(10)),
                "Modifying an emprunt with a non-existent ID should throw EmpruntNotFoundException.");
    }

    @Test
    void testProlongerDateRetourSuccess() throws EmpruntExisteException, DateInacceptableException {
        Emprunt emprunt = new Emprunt(1, "Book Title", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        LocalDate newReturnDate = LocalDate.now().plusDays(10);
        empruntService.prolongerDateRetour(emprunt, newReturnDate);

        assertEquals(newReturnDate, emprunt.getDateRetour(), "Return date should be updated to the new value.");
    }

    @Test
    void testProlongerDateRetourThrowsExceptionForInvalidDate() {
        Emprunt emprunt = new Emprunt(1, "Book Title", LocalDate.now(), LocalDate.now().plusDays(7));
        assertDoesNotThrow(() -> empruntService.ajouterEmprunt(emprunt));

        LocalDate invalidReturnDate = LocalDate.now().plusDays(5);
        assertThrows(DateInacceptableException.class, () ->
                empruntService.prolongerDateRetour(emprunt, invalidReturnDate),
                "Setting a return date earlier than the current one should throw DateInacceptableException.");
    }

    @Test
    void testRechercherParIdSuccess() throws EmpruntExisteException {
        Emprunt emprunt = new Emprunt(1, "Book Title", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        Emprunt foundEmprunt = empruntService.rechercherParId(emprunt.getId());
        assertNotNull(foundEmprunt, "Emprunt should be found.");
        assertEquals(emprunt, foundEmprunt, "The returned emprunt should match the expected emprunt.");
    }

    @Test
    void testRechercherParIdReturnsNullForInvalidId() {
        assertNull(empruntService.rechercherParId(999), "Searching for a non-existent ID should return null.");
    }	









 /*   private IEmpruntImpl empruntService;

    @BeforeEach
    public void setUp() {
        empruntService = new IEmpruntImpl();
    }

    @Test
    public void testAjouterEmprunt() throws EmpruntExisteException {
        Emprunt emprunt = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        List<Emprunt> emprunts = empruntService.getListe();
        assertEquals(1, emprunts.size());
        assertEquals(emprunt, emprunts.get(0));
    }

    @Test
    public void testAjouterEmpruntExistant() {
        Emprunt emprunt = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        assertDoesNotThrow(() -> empruntService.ajouterEmprunt(emprunt));

        assertThrows(EmpruntExisteException.class, () -> empruntService.ajouterEmprunt(emprunt));
    }

    /*  @Test
  public void testSupprimerEmprunt() throws EmpruntExisteException, EmpruntNotFoundException {
        Emprunt emprunt = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        empruntService.supprimerEmprunt(1);

        assertTrue(empruntService.getListe().isEmpty());
    }

    
    @Test
    public void testSupprimerEmpruntNonExistant() {
        assertThrows(EmpruntNotFoundException.class, () -> empruntService.supprimerEmprunt(999));
    }

    @Test
    public void testModifierEmprunt() throws EmpruntExisteException, EmpruntNotFoundException {
        Emprunt emprunt = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        empruntService.modifierEmprunt(1, 102, "Livre B", LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));

        Emprunt modifie = empruntService.rechercherParId(1);
        assertNotNull(modifie);
        assertEquals(102, modifie.getIdUtilisateur());
        assertEquals("Livre B", modifie.getTitreLivre());
        assertEquals(LocalDate.now().minusDays(1), modifie.getDateEmprunt());
        assertEquals(LocalDate.now().plusDays(14), modifie.getDateRetour());
    }

    @Test
    public void testModifierEmpruntNonExistant() {
        assertThrows(EmpruntNotFoundException.class, () -> 
            empruntService.modifierEmprunt(999, 102, "Livre B", LocalDate.now(), LocalDate.now().plusDays(14))
        );
    }

    @Test
    public void testProlongerDateRetour() throws EmpruntExisteException, DateInacceptableException {
        Emprunt emprunt = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        LocalDate nouvelleDate = LocalDate.now().plusDays(10);
        empruntService.prolongerDateRetour(emprunt, nouvelleDate);

        assertEquals(nouvelleDate, emprunt.getDateRetour());
    }

    @Test
    public void testProlongerDateRetourInvalide() throws EmpruntExisteException {
        Emprunt emprunt = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        LocalDate nouvelleDate = LocalDate.now().plusDays(5);
        assertThrows(DateInacceptableException.class, () -> empruntService.prolongerDateRetour(emprunt, nouvelleDate));
    }

   
     @Test
    public void testRechercherParId() throws EmpruntExisteException {
        Emprunt emprunt = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        empruntService.ajouterEmprunt(emprunt);

        Emprunt trouve = empruntService.rechercherParId(1);
        assertNotNull(trouve);
        assertEquals(emprunt, trouve);
    }
   
     @Test
        public void testRechercherParIdInexistant() {
        Emprunt emprunt = empruntService.rechercherParId(999);
        assertNull(emprunt);
    }
    

    @Test
    public void testTrierListeEmpruntsParDateEmprunt() throws EmpruntExisteException {
        Emprunt e1 = new Emprunt(101, "Livre A", LocalDate.now(), LocalDate.now().plusDays(7));
        Emprunt e2 = new Emprunt(102, "Livre B", LocalDate.now().minusDays(1), LocalDate.now().plusDays(10));
        empruntService.ajouterEmprunt(e1);
        empruntService.ajouterEmprunt(e2);

        empruntService.trierListeEmpruntsParDateEmprunt();

        List<Emprunt> emprunts = empruntService.getListe();
        assertEquals(e2, emprunts.get(0));
        assertEquals(e1, emprunts.get(1));
    }*/
}