package models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe représentant un utilisateur avec des informations personnelles
 * telles que l'identifiant, le nom, le prénom, les droits d'accès, le mot de passe,
 * le numéro de téléphone et l'adresse e-mail.
 */
public class Utilisateur implements Comparable<Utilisateur> {

    private int id;
    private String nom;
    private String prenom;
    private String droitAcces;
    private String password;
    private int numerotel;
    private String Email;
    private LocalDate dateUtilisateur;
    private static int compteur;

    /**
     * Constructeur par défaut.
     * Génère automatiquement un identifiant unique pour chaque utilisateur.
     */
    public Utilisateur() {
        super();
        compteur++;
        id = compteur;
    }

    /**
     * Constructeur paramétré.
     *
     * @param nom        Le nom de l'utilisateur.
     * @param prenom     Le prénom de l'utilisateur.
     * @param droitAcces Les droits d'accès de l'utilisateur.
     * @param password   Le mot de passe de l'utilisateur.
     * @param numerotel  Le numéro de téléphone de l'utilisateur.
     * @param Email      L'adresse e-mail de l'utilisateur.
     */
    public Utilisateur(String nom, String prenom, String droitAcces, String password, int numerotel, String Email,LocalDate date) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.droitAcces = droitAcces;
        this.password = password;
        this.Email = Email;
        this.numerotel = numerotel;
        this.dateUtilisateur=date;
        compteur++;
        id = compteur;
    }

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param id L'identifiant à définir.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur.
     *
     * @param nom Le nom à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'utilisateur.
     *
     * @param prenom Le prénom à définir.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne les droits d'accès de l'utilisateur.
     *
     * @return Les droits d'accès de l'utilisateur.
     */
    public String getDroitAcces() {
        return droitAcces;
    }

    /**
     * Définit les droits d'accès de l'utilisateur.
     *
     * @param droitAcces Les droits d'accès à définir.
     */
    public void setDroitAcces(String droitAcces) {
        this.droitAcces = droitAcces;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param password Le mot de passe à définir.
     */
    public void setpassword(String password) {
        this.password = password;
    }

    /**
     * Retourne le numéro de téléphone de l'utilisateur.
     *
     * @return Le numéro de téléphone de l'utilisateur.
     */
    public int getNumerotel() {
        return numerotel;
    }

    /**
     * Définit le numéro de téléphone de l'utilisateur.
     *
     * @param numerotel Le numéro de téléphone à définir.
     */
    public void setNumerotel(int numerotel) {
        this.numerotel = numerotel;
    }

    /**
     * Retourne l'adresse e-mail de l'utilisateur.
     *
     * @return L'adresse e-mail de l'utilisateur.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Définit l'adresse e-mail de l'utilisateur.
     *
     * @param email L'adresse e-mail à définir.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'utilisateur.
     *
     * @return Une chaîne contenant les informations de l'utilisateur.
     */
    @Override
    public String toString() {
        return id + ";" + nom + ";" + prenom + ";" + droitAcces + ";" + password + ";" + numerotel + ";" + Email + ";" + dateUtilisateur ;
    }

    /**
     * Compare l'égalité entre deux utilisateurs en se basant sur l'adresse e-mail.
     *
     * @param obj L'objet à comparer.
     * @return `true` si les utilisateurs ont la même adresse e-mail, `false` sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Utilisateur other = (Utilisateur) obj;
        return Objects.equals(Email, other.Email);
    }
    
    public LocalDate getDateUtilisateur() {
		return dateUtilisateur;
	}

	public void setDateUtilisateur(LocalDate dateUtilisateur) {
		this.dateUtilisateur = dateUtilisateur;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * Compare deux utilisateurs par leur nom (ordre alphabétique, insensible à la casse).
     *
     * @param autre L'autre utilisateur à comparer.
     * @return Une valeur négative, zéro ou positive selon l'ordre de tri.
     */
    @Override
    public int compareTo(Utilisateur autre) {
        return this.nom.compareToIgnoreCase(autre.nom);
    }
}
