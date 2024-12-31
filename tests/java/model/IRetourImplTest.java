package model;


import exceptions.RetourExisteException;
import exceptions.RetourNotFoundException;
import models.IRetourImpl;
import models.Retour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;





public class IRetourImplTest {
	
    private IRetourImpl retourService;

    @BeforeEach
    public void setUp() {
        retourService = new IRetourImpl();
    }

    @Test
    public void testAjouterRetour_Success() throws RetourExisteException {
        Retour retour = new Retour(1, LocalDate.now(), 0);
        retourService.ajouterRetour(retour);

        List<Retour> retours = retourService.getListeRetour();
        assertEquals(1, retours.size());
        assertEquals(retour, retours.get(0));
    }

    @Test
    public void testAjouterRetour_ThrowsRetourExisteException() throws RetourExisteException {
        Retour retour = new Retour(1, LocalDate.now(), 0);
        retourService.ajouterRetour(retour);

        assertThrows(RetourExisteException.class, () -> retourService.ajouterRetour(retour));
    }

    @Test
    public void testSupprimerRetour_Success() throws RetourNotFoundException, RetourExisteException {
        Retour retour = new Retour(1, LocalDate.now(), 0);
        retourService.ajouterRetour(retour);

        retourService.supprimerRetour(retour.getId());

        List<Retour> retours = retourService.getListeRetour();
        assertTrue(retours.isEmpty());
    }

    @Test
    public void testSupprimerRetour_ThrowsRetourNotFoundException() {
        assertThrows(RetourNotFoundException.class, () -> retourService.supprimerRetour(999));
    }

    @Test
    public void testModifierRetour_Success() throws RetourNotFoundException, RetourExisteException {
        Retour retour = new Retour(1, LocalDate.now(), 0);
        retourService.ajouterRetour(retour);

        LocalDate newDate = LocalDate.now().plusDays(5);
        retourService.modifierRetour(retour.getId(), 2, newDate, 1);

        Retour modifiedRetour = retourService.rechercherParId(retour.getId());
        assertNotNull(modifiedRetour);
        assertEquals(2, modifiedRetour.getIdEmprunt());
        assertEquals(newDate, modifiedRetour.getDateRetourActuelle());
        assertEquals(1, modifiedRetour.getRetard());
    }

    @Test
    public void testModifierRetour_ThrowsRetourNotFoundException() {
        assertThrows(RetourNotFoundException.class, () -> {
            retourService.modifierRetour(999, 2, LocalDate.now(), 1);
        });
    }

    @Test
    public void testRechercherParId_Success() throws RetourExisteException {
        Retour retour = new Retour(1, LocalDate.now(), 0);
        retourService.ajouterRetour(retour);

        Retour foundRetour = retourService.rechercherParId(retour.getId());
        assertNotNull(foundRetour);
        assertEquals(retour, foundRetour);
    }

    @Test
    public void testRechercherParId_NotFound() {
        Retour retour = retourService.rechercherParId(999);
        assertNull(retour);
    }

    @Test
    public void testSortingById() throws RetourExisteException {
        Retour r1 = new Retour(1, LocalDate.now(), 0);
        Retour r2 = new Retour(2, LocalDate.now().plusDays(1), 1);

        retourService.ajouterRetour(r2);
        retourService.ajouterRetour(r1);

        retourService.trierListeRetourParId();
        List<Retour> sortedList = retourService.getListeRetour();

        assertEquals(r1.getId(), sortedList.get(0).getId());
        assertEquals(r2.getId(), sortedList.get(1).getId());
    }

    @Test
    public void testSortingByDateRetourActuelle() throws RetourExisteException {
        Retour r1 = new Retour(1, LocalDate.now().plusDays(2), 0);
        Retour r2 = new Retour(2, LocalDate.now(), 1);

        retourService.ajouterRetour(r1);
        retourService.ajouterRetour(r2);

        retourService.trierListeRetourParDateRetourActuelle();
        List<Retour> sortedList = retourService.getListeRetour();

        assertEquals(r2, sortedList.get(0));
        assertEquals(r1, sortedList.get(1));
    }

    @Test
    public void testSortingByRetard() throws RetourExisteException {
        Retour r1 = new Retour(1, LocalDate.now(), 5);
        Retour r2 = new Retour(2, LocalDate.now().plusDays(1), 3);

        retourService.ajouterRetour(r1);
        retourService.ajouterRetour(r2);

        retourService.trierListeRetoursParRetard();
        List<Retour> sortedList = retourService.getListeRetour();

        assertEquals(r2, sortedList.get(0));
        assertEquals(r1, sortedList.get(1));
    }

    @Test
    public void testFileOperations() {
        retourService = new IRetourImpl("test_retours.csv");
        Retour retour = new Retour(1, LocalDate.now(), 0);

        try {
            retourService.ajouterRetour(retour);
            retourService.sauvegarderRetoursCSV();
            retourService.lireRetoursCSV();
        } catch (Exception e) {
            fail("File operations should not throw exceptions: " + e.getMessage());
        }

        List<Retour> retours = retourService.getListeRetour();
        assertFalse(retours.isEmpty());
    }

}