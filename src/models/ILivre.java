package models;

import exceptions.*;
import java.time.LocalDate;
import java.util.List;

public interface ILivre {

    // Adds a book to the list
    void ajouterLivre(Livre livre) throws LivreExisteException;

    // Removes a book from the list by ID
    void supprimerLivre(int id) throws LivreNotFoundException;

    // Modifies the details of a book
    void modifierLivre(int id,String newTitre,String newAuteur,int newAnneePublication,String newGenre,String newIsbn,String newEditeur,String newLangue,int newDisponibilite) throws LivreNotFoundException;

    // Searches for a book by ID
    Livre rechercherParId(int id);

    // Lists all books
    void listerLivres();

    // Sorts books by title
    void trierListeLivresParTitre();

    // Sorts books by author
    void trierListeLivresParAuteur();

    // Sorts books by ISBN
    void trierListeLivresParIsbn();

    // Sorts books by publication date
    void trierListeLivresParDatePublication();

    // Sorts books by availability
    void trierListeLivresParDisponibilite();

    // Saves books to a CSV file
    void sauvegarderLivresCSV();

    // Loads books from a CSV file
    void lireLivresCSV();

    // Gets the list of books
    List<Livre> getListe();
    
    //search the list of books 
    public List<Livre> recherche(String searchQuery);
    
    public void trierLivresParId() ;

    public void trierLivresParTitre() ;

    public void trierLivresParAuteur() ;

    public void trierLivresParAnneePublication() ;
    
    public void trierLivresParGenre() ;

    public void trierLivresParIsbn() ;

    public void trierLivresParEditeur() ;

    public void trierLivresParLangue() ;
   
    public void trierLivresParDisponibilite();

}