package models;

//import java.time.LocalDate;

public class Livre implements Comparable<Livre> {
    private int id;
    private static int compteur = 0; // Compteur statique pour générer des IDs uniques.

    public static int getCompteur() {
        return compteur;
    }

    public static void setCompteur(int compteur) {
        Livre.compteur = compteur;
    }

    private String titre;
    private String auteur;
    private int anneePublication;
    private String genre;
    //private float prix;
    private String isbn; // Identifiant unique pour les livres.
    private String editeur; // Maison d'édition.
    //private int nbPages; // Nombre de pages.
    private String langue; // Langue du livre.
    //private LocalDate dateAjout; // Utilise LocalDate pour la date
    private int disponibilite; // Nombre de livres en stock.

    // Constructeur par défaut
    public Livre() {
        compteur++;
        this.id = compteur;
       // this.dateAjout = LocalDate.now(); // La date d'ajout est automatiquement assignée à la date actuelle
        this.disponibilite = 0; // Initialisation de la disponibilité à 0 par défaut
    }

    // Constructeur
    public Livre(String titre, String auteur, int anneePublication, String genre, String isbn, String editeur, String langue, int disponibilite) {
        this.id = ++compteur;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
       // this.prix = prix;
        this.isbn = isbn;
        this.editeur = editeur;
       // this.nbPages = nbPages;
        this.langue = langue;
        this.disponibilite = disponibilite;
       // this.dateAjout = LocalDate.now(); // Initialisation par défaut
    }

    // Getter et setter pour dateAjout
  


    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

//    public float getPrix() {
//        return prix;
//    }
//
//    public void setPrix(float prix) {
//        this.prix = prix;
//    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

//    public int getNbPages() {
//        return nbPages;
//    }
//
//    public void setNbPages(int nbPages) {
//        this.nbPages = nbPages;
//    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

//    public LocalDate getDateAjout() {
//        return dateAjout;
//    }
//
//    public void setDateAjout(LocalDate dateAjout) {
//        this.dateAjout = dateAjout;
//    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return id + ";" + titre + ";" + auteur + ";" + genre + ";" + anneePublication + ";" + 
               isbn + ";" + editeur + ";" + langue + ";" + disponibilite;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Livre autreLivre = (Livre) obj;
        return this.isbn.equals(autreLivre.isbn); // Comparaison par ISBN (identifiant unique).
    }

    @Override
    public int compareTo(Livre autreLivre) {
        return this.titre.compareTo(autreLivre.titre); // Comparaison par titre.
    }

}
