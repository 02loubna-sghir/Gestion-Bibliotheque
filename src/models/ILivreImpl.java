package models;

import exceptions.*;
import java.io.*;
//import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ILivreImpl implements ILivre {

    private ArrayList<Livre> liste = new ArrayList<Livre>();
    private String filename;

    public ILivreImpl() {
    }

    public ILivreImpl(String fn) {
        this.filename = fn;
    }

    @Override
    public void ajouterLivre(Livre livre) throws LivreExisteException {
        // Check if the book already exists
        for (Livre l : liste) {
            if (l.equals(livre)) {
                throw new LivreExisteException("Le livre existe déjà");
            }
        }
        liste.add(livre);
    }

    @Override
    public void supprimerLivre(int id) throws LivreNotFoundException {
        Livre l = rechercherParId(id);
        if (l == null) {
            throw new LivreNotFoundException("Le livre n'existe pas");
        } else {
            liste.remove(l);
        }
    }

    @Override
    public void modifierLivre(int id,String newTitre,String newAuteur,int newAnneePublication,String newGenre,String newIsbn,String newEditeur,String newLangue,int newDisponibilite) throws LivreNotFoundException {
        Livre l = rechercherParId(id);
        if (l == null)
            throw new LivreNotFoundException("Le livre n'existe pas");
        else {
            l.setTitre(newTitre);
            l.setAuteur(newAuteur);
            l.setGenre(newGenre);
            l.setIsbn(newIsbn);
            l.setEditeur(newEditeur);
            l.setLangue(newLangue);
            l.setDisponibilite(newDisponibilite);
        }
    }

    @Override
    public Livre rechercherParId(int id) {
        return liste.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void listerLivres() {
        liste.forEach(System.out::println);
    }

    @Override
    public void trierListeLivresParTitre() {
        liste.sort(Comparator.comparing(Livre::getTitre));
    }

    @Override
    public void trierListeLivresParAuteur() {
        liste.sort(Comparator.comparing(Livre::getAuteur));
    }

    @Override
    public void trierListeLivresParIsbn() {
        liste.sort(Comparator.comparing(Livre::getIsbn));
    }

    @Override
    public void trierListeLivresParDatePublication() {
        liste.sort(Comparator.comparing(Livre::getAnneePublication));
    }

    @Override
    public void trierListeLivresParDisponibilite() {
        liste.sort(Comparator.comparing(Livre::getDisponibilite));
    }

    @Override
    public void sauvegarderLivresCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // En-tête du fichier CSV avec les attributs de Livre
            bw.write("id;titre;auteur;genre;anneePublication;isbn;editeur;langue;disponibilite");
            bw.newLine(); // nouvelle ligne après l'entête

            // Parcours de la liste des livres et écriture dans le fichier CSV
            for (Livre livre : liste) {
                bw.write(livre.getId() + ";" + 
                         livre.getTitre() + ";" + 
                         livre.getAuteur() + ";" + 
                         livre.getGenre() + ";" + 
                         livre.getAnneePublication() + ";" + 
                         livre.getIsbn() + ";" + 
                         livre.getEditeur() + ";" + 
                         livre.getLangue() + ";" + 
                         livre.getDisponibilite());
                bw.newLine(); // nouvelle ligne après chaque livre
            }

            System.out.println("Fichier sauvegardé avec succès : " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void lireLivresCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // sauter la ligne d'entête

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(";");

                // Récupérer les valeurs des attributs du livre à partir de la ligne CSV
                String titre = words[1];
                String auteur = words[2];
                String genre = words[3];
                int anneePublication = Integer.parseInt(words[4]);
                String isbn = words[5];
                String editeur = words[6];
                String langue = words[7];
                int disponibilite = Integer.parseInt(words[8]);

                // Création d'un livre avec les données lues
                Livre livre = new Livre(titre, auteur, anneePublication, genre, isbn, editeur, langue, disponibilite);
                liste.add(livre); // Ajout du livre dans la liste
            }
            System.out.println("Fichier lu avec succès : " + filename);
        } catch (IOException e) {
            System.err.println("Le fichier n'existe pas!");
        }
    }


    @Override
    public List<Livre> getListe() {
        return liste;
    }

    @Override
    public List<Livre> recherche(String searchQuery) {
        // Convertir la recherche en minuscule pour rendre la recherche insensible à la casse
        String lowerCaseQuery = searchQuery.toLowerCase();

        // Filtrer les livres dont les champs contiennent le mot-clé de recherche
        return liste.stream()
                    .filter(livre -> livre.getTitre().toLowerCase().contains(lowerCaseQuery) || 
                                     livre.getAuteur().toLowerCase().contains(lowerCaseQuery) || 
                                     livre.getGenre().toLowerCase().contains(lowerCaseQuery) ||
                                     livre.getIsbn().toLowerCase().contains(lowerCaseQuery) ||
                                     livre.getEditeur().toLowerCase().contains(lowerCaseQuery) ||
                                     livre.getLangue().toLowerCase().contains(lowerCaseQuery))
                    .collect(Collectors.toList());
    }
    
    @Override
    public void trierLivresParId() {
        liste.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    }

    @Override
    public void trierLivresParTitre() {
        liste.sort((o1, o2) -> o1.getTitre().compareTo(o2.getTitre()));
    }

    @Override
    public void trierLivresParAuteur() {
        liste.sort((o1, o2) -> o1.getAuteur().compareTo(o2.getAuteur()));
    }

    @Override
    public void trierLivresParAnneePublication() {
        liste.sort((o1, o2) -> Integer.compare(o1.getAnneePublication(), o2.getAnneePublication()));
    }

    @Override
    public void trierLivresParGenre() {
        liste.sort((o1, o2) -> o1.getGenre().compareTo(o2.getGenre()));
    }

    @Override
    public void trierLivresParIsbn() {
        liste.sort((o1, o2) -> o1.getIsbn().compareTo(o2.getIsbn()));
    }

    @Override
    public void trierLivresParEditeur() {
        liste.sort((o1, o2) -> o1.getEditeur().compareTo(o2.getEditeur()));
    }

    @Override
    public void trierLivresParLangue() {
        liste.sort((o1, o2) -> o1.getLangue().compareTo(o2.getLangue()));
    }

    @Override
    public void trierLivresParDisponibilite() {
        liste.sort((o1, o2) -> Integer.compare(o1.getDisponibilite(), o2.getDisponibilite()));
    }

	

}
