package models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.*;
import exceptions.UtilisateurExsiste;
import exceptions.UtilisateurNotFoundException;

/**
 * Classe représentant un modèle pour la gestion des utilisateurs.
 * Permet l'ajout, la modification, la suppression, la recherche et
 * le tri des utilisateurs, ainsi que la persistance des données via un fichier CSV.
 */
public class UtilisateurModel implements UtilisateurModelInterface {

    /**
     * Liste des utilisateurs gérée par le modèle.
     */
    private ArrayList<Utilisateur> liste = new ArrayList<>();

    /**
     * Chemin vers le fichier CSV utilisé pour la persistance des utilisateurs.
     */
    private String csvUtilisateur;

    /**
     * Constructeur par défaut.
     */
    public UtilisateurModel() {
        super();
    }

    /**
     * Constructeur avec un chemin vers un fichier CSV.
     *
     * @param csvUtilisateur Le chemin du fichier CSV.
     */
    public UtilisateurModel(String csvUtilisateur) {
        super();
        this.csvUtilisateur = csvUtilisateur;
    }

    /**
     * Ajoute un utilisateur à la liste après vérification de son unicité.
     *
     * @param u L'utilisateur à ajouter.
     * @throws UtilisateurExsiste Si un utilisateur identique existe déjà.
     */
    @Override
    public void ajouterUtilisateur(Utilisateur u) throws UtilisateurExsiste {
        for (Utilisateur utilisateur : liste) {
            if (utilisateur.equals(u)) {
                throw new UtilisateurExsiste("L'utilisateur existe déjà");
            }
        }
        u.setId(genererIdUnique());
        liste.add(u);
    }

    /**
     * Modifie les informations d'un utilisateur existant.
     *
     * @param id             L'ID de l'utilisateur à modifier.
     * @param nouveauNom     Le nouveau nom de l'utilisateur.
     * @param nouveauPrenom  Le nouveau prénom de l'utilisateur.
     * @param nouveauDroitAcces Le nouveau droit d'accès de l'utilisateur.
     * @param nouveauPassword Le nouveau mot de passe de l'utilisateur.
     * @param nouveaunum     Le nouveau numéro de téléphone de l'utilisateur.
     * @param nouveauEmail   La nouvelle adresse email de l'utilisateur.
     * @param nouveauDate   La nouvelle date  de l'utilisateur.
     * @throws UtilisateurNotFoundException Si l'utilisateur n'existe pas.
     */
    @Override
    public void modifierUtilisateur(int id, String nouveauNom, String nouveauPrenom, String nouveauDroitAcces,
                                     String nouveauPassword, int nouveaunum, String nouveauEmail,LocalDate nouveauDate) throws UtilisateurNotFoundException {
        Utilisateur e = rechercherParID(id);
        if (e == null) {
            throw new UtilisateurNotFoundException("L'utilisateur n'existe pas");
        } else {
            e.setNom(nouveauNom);
            e.setPrenom(nouveauPrenom);
            e.setDroitAcces(nouveauDroitAcces);
            e.setpassword(nouveauPassword);
            e.setNumerotel(nouveaunum);
            e.setEmail(nouveauEmail);
            e.setDateUtilisateur(nouveauDate);
        }
    }

    /**
     * Supprime un utilisateur de la liste.
     *
     * @param id L'ID de l'utilisateur à supprimer.
     * @throws UtilisateurNotFoundException Si l'utilisateur n'existe pas.
     */
    @Override
    public void supprimerUtilisateur(int id) throws UtilisateurNotFoundException {
        Utilisateur e = rechercherParID(id);
        if (e == null) {
            throw new UtilisateurNotFoundException(id);
        } else {
            liste.remove(e);
        }
    }

    /**
     * Trie la liste des utilisateurs par ordre croissant d'ID.
     */
    @Override
    public void trierListeUtilisateurs() {
        Collections.sort(liste);
    }

    /**
     * Recherche un utilisateur par son ID.
     *
     * @param id L'ID de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant, ou null s'il n'existe pas.
     */
    @Override
    public Utilisateur rechercherParID(int id) {
        Optional<Utilisateur> e = liste.stream().filter(t -> t.getId() == id).findFirst();
        return e.orElse(null);
    }

    /**
     * Recherche les utilisateurs ayant un nom donné.
     *
     * @param nom Le nom à rechercher.
     * @return Une liste d'utilisateurs correspondant au nom.
     */
    public List<Utilisateur> rechercherParNom(String nom) {
        return liste.stream()
                .filter(t -> t.getNom().equals(nom))
                .collect(Collectors.toList());
    }
    /**
     * Trie les utilisateurs par prénom en ordre croissant.
     */
    /**
     * Trie les utilisateurs par ID en ordre croissant.
     */
    public void trierParId() {
        Collections.sort(liste, (u1, u2) -> Integer.compare(u1.getId(), u2.getId()));
    }

    /**
     * Trie les utilisateurs par nom en ordre croissant.
     */
    public void trierParNom() {
        Collections.sort(liste, (u1, u2) -> u1.getNom().compareTo(u2.getNom()));
    }

    /**
     * Trie les utilisateurs par prénom en ordre croissant.
     */
    public void trierParPrenom() {
        Collections.sort(liste, (u1, u2) -> u1.getPrenom().compareTo(u2.getPrenom()));
    }

    /**
     * Trie les utilisateurs par numéro de téléphone en ordre croissant.
     */
    public void trierParNumeroTel() {
        Collections.sort(liste, (u1, u2) -> Integer.compare(u1.getNumerotel(), u2.getNumerotel()));
    }

    /**
     * Trie les utilisateurs par adresse e-mail en ordre croissant.
     */
    public void trierParEmail() {
        Collections.sort(liste, (u1, u2) -> u1.getEmail().compareTo(u2.getEmail()));
    }

    /**
     * Trie les utilisateurs par droit d'accès en ordre croissant.
     */
    public void trierParDroitAcces() {
        Collections.sort(liste, (u1, u2) -> u1.getDroitAcces().compareTo(u2.getDroitAcces()));
    }

    /**
     * Trie les utilisateurs par date d'ajout en ordre croissant.
     */
    public void trierParDate() {
        Collections.sort(liste, (u1, u2) -> u1.getDateUtilisateur().compareTo(u2.getDateUtilisateur()));
    }

    /**
     * Trie les utilisateurs par mot de passe en ordre croissant (lexicographique).
     */
    public void trierParPassword() {
        Collections.sort(liste, (u1, u2) -> u1.getPassword().compareTo(u2.getPassword()));
    }

    public List<Utilisateur> rechercherParDate(java.util.Date date) {
        return liste.stream()
                .filter(t -> t.getDateUtilisateur() != null && t.getDateUtilisateur().equals(date))
                .collect(Collectors.toList());
    }



    /**
     * Affiche tous les utilisateurs dans la console.
     */
    @Override
    public void listerUtilisateurs() {
        System.out.println(liste);
    }

    /**
     * Sauvegarde la liste des utilisateurs dans un fichier CSV.
     */
    @Override
    public void sauvegarderCSV() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(csvUtilisateur));
            bw.write("id;Nom;Prenom;DroitAcces;Password;Numero telephone;Adresse mail;Date d ajout");
            for (int i = 0; i < liste.size(); i++) {
                Utilisateur e1 = liste.get(i);
                bw.newLine();
                bw.write(e1.toString());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge les utilisateurs depuis un fichier CSV.
     */
    @Override
    public void lireCSV() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvUtilisateur));
            br.readLine(); // Ignorer le header
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(";");
                int id = Integer.parseInt(words[0]);
                String nom = words[1];
                String prenom = words[2];
                String droitAcces = words[3];
                String password = words[4];
                int numeTelep = Integer.parseInt(words[5]);
                String email = words[6];
                LocalDate date = LocalDate.parse(words[7]);
                Utilisateur e = new Utilisateur();
                e.setId(id);
                e.setNom(nom);
                e.setPrenom(prenom);
                e.setDroitAcces(droitAcces);
                e.setpassword(password);
                e.setEmail(email);
                e.setNumerotel(numeTelep);
                e.setDateUtilisateur(date);
                liste.add(e);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Le fichier n'existe pas!");
        }
    }

    /**
     * Génère un ID unique pour un nouvel utilisateur.
     *
     * @return Un ID unique.
     */
    public int genererIdUnique() {
        if (liste.isEmpty()) {
            return 1;
        } else {
            return liste.stream()
                    .mapToInt(Utilisateur::getId)
                    .max()
                    .getAsInt() + 1;
        }
    }

    /**
     * Retourne la liste des utilisateurs.
     *
     * @return La liste des utilisateurs.
     */
    public ArrayList<Utilisateur> getListe() {
        return liste;
    }
}
