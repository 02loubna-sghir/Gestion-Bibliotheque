package models;

import java.time.LocalDate;





import java.io.*;

import exceptions.*;

import java.util.*;

public class IEmpruntImpl implements IEmprunt {

	private ArrayList<Emprunt> liste = new ArrayList<Emprunt>();

	private String filename;

	public IEmpruntImpl() {
	}

	public IEmpruntImpl(String fn) {

		this.filename = fn;

	}
	
	
	public void ajouterEmprunt(Emprunt emp) throws EmpruntExisteException {int i; int exi=0;
		for(i=0;i<liste.size();i++) {
		if(liste.get(i).equals(emp)){
			exi=1;
			throw new EmpruntExisteException("l'emprunt existe déjà");
		}
	}
		if(exi==0) {
			emp.setId(genererIdUnique());
			liste.add(emp);
		}
	}
		
	
	
	public void supprimerEmprunt(int id) throws EmpruntNotFoundException {
		
		Emprunt e = rechercherParId(id);
		if(e==null) {
			throw new EmpruntNotFoundException("L'emprunt n'existe pas");
		}
		else {
			liste.remove(e);
		}
		
	}
	
	
	
	
	
	public void modifierEmprunt(int id, int idutili, String titrelivr, LocalDate de, LocalDate dr ) throws EmpruntNotFoundException{	
		Emprunt e=rechercherParId(id);
		if(e==null)
			throw new EmpruntNotFoundException("L'emprunt n'existe pas");
		else {
			
			e.setDateEmprunt(de);
			e.setDateRetour(dr);
			e.setIdUtilisateur(idutili);
			e.setTitreLivre(titrelivr);
			
			
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	public Emprunt rechercherParId(int id) {
		
		
		Optional<Emprunt> e=liste.stream().filter(t->t.getId()==id).findFirst();
		if(e.isPresent()) {
			return e.get();
		}
		else {
			return null;
		}
		
		
	}
	
	
	public void listerEmprunts() {
		
		System.out.println(liste.toString());	
		
	}
	
	
	
	
	
	
	
	
	public void trierListeEmpruntsParDateEmprunt() {
		liste.sort((o1,o2)->o1.getDateEmprunt().compareTo(o2.getDateEmprunt()));
	}
	
	
	
	
	public void trierListeEmpruntsParDateRetour() {
		liste.sort((o1,o2)->o1.getDateRetour().compareTo(o2.getDateRetour()));
	}
	
	
	public void trierListeEmpruntsParId() {
		liste.sort((o1,o2)->Integer.compare(o1.getId(), o2.getId()));
	}
	
	public void trierListeEmpruntsParIdUtilisateur() {
		liste.sort((o1,o2)->Integer.compare(o1.getIdUtilisateur(), o2.getIdUtilisateur()));
	}
	
	
	public void trierListeEmpruntsParTitreLivre() {
		liste.sort((o1,o2)->o1.getTitreLivre().compareTo(o2.getTitreLivre()));
	}
	
	
	
	
	public void sauvegarderEmpruntsCSV() {
		try {
	        File file = new File(filename);
			BufferedWriter bw= new BufferedWriter(new FileWriter(filename));
			bw.write("id;id utilisteur;titre livre;date emprunt;date retour");
			for(int i=0;i<liste.size();i++) {
				Emprunt e1=liste.get(i);
				bw.newLine();
				bw.write(e1.toString());
				
			}
			bw.close();
			System.out.println("Fichier sauvegardé avec succès : " + file.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void lireEmpruntsCSV() {
		try {
			BufferedReader br =new BufferedReader(new FileReader(filename));
			br.readLine();
			String line;
			while((line=br.readLine())!=null) {
				String[] words= line.split(";");
				int id=Integer.parseInt(words[0]);
				int idUtili=Integer.parseInt(words[1]);
				String titreLivr=words[2];
				LocalDate dateEmp = LocalDate.parse(words[3]);
				LocalDate dateRet = LocalDate.parse(words[4]);
				
				Emprunt e=new Emprunt();
				e.setId(id);
				e.setIdUtilisateur(idUtili);
				e.setTitreLivre(titreLivr);
				e.setDateEmprunt(dateEmp);
				e.setDateRetour(dateRet);
				liste.add(e);
				
			}
			
			br.close();
		} catch (IOException e) {
			System.err.println("le fichier n'existe pas!");
		}
		
		
	}
	
	
	

	public void prolongerDateRetour(Emprunt empru, LocalDate nouvelledate) throws DateInacceptableException {

		if (nouvelledate.isAfter(empru.getDateRetour())) {
			empru.setDateRetour(nouvelledate);
		
		} else {
			throw new DateInacceptableException("la date est antérieur à la date de retour déja précisé");
		}
	}
	
	
	public int genererIdUnique() {
        if (liste.isEmpty()) {
            return 1;
        } else {
            return liste.stream()
                    .mapToInt(Emprunt::getId)
                    .max()
                    .getAsInt() + 1;
        }
    }
	
	
	
	
	
	
	public List<Emprunt> getListe() {
	    return liste;
	}
	
	
	
	public Map<String, Integer> calculerFrequenceLivres() {
	    // Créer un dictionnaire (Map) pour stocker la fréquence des livres
	    Map<String, Integer> frequenceLivres = new HashMap<>();

	    // Parcourir chaque emprunt dans la liste des emprunts
	    for (Emprunt emprunt : liste) {
	        String titreLivre = emprunt.getTitreLivre(); // Récupérer le titre du livre

	        // Si le livre est déjà dans le map, incrémenter sa fréquence, sinon l'ajouter avec une fréquence de 1
	        frequenceLivres.put(titreLivre, frequenceLivres.getOrDefault(titreLivre, 0) + 1);
	    }

	    // Retourner la map contenant la fréquence des livres
	    return frequenceLivres;
	}
	public void viderListe() {
		liste.clear();
	}

}
