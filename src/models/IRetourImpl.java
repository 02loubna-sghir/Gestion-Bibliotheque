package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.RetourExisteException;
import exceptions.RetourNotFoundException;

public class IRetourImpl implements IRetour{
	
	private ArrayList<Retour> listeRetour = new ArrayList<Retour>();

	private String filename;

	public IRetourImpl() {
	}

	public IRetourImpl(String fn) {

		this.filename = fn;

	}
	
	
	public void ajouterRetour(Retour retou) throws RetourExisteException {int i; int exi=0;
		for(i=0;i<listeRetour.size();i++) {
		if(listeRetour.get(i).equals(retou)){
			exi=1;
			throw new RetourExisteException("le retour existe déjà");
		}
	}
		if(exi==0) {
			retou.setId(genererIdUnique());
			listeRetour.add(retou);
		}
	}
		
	
	
	public void supprimerRetour(int id) throws RetourNotFoundException {
		
		Retour e = rechercherParId(id);
		if(e==null) {
			throw new RetourNotFoundException("Le retour n'existe pas");
		}
		else {
			listeRetour.remove(e);
		}
		
	}
	
	
	
	
	
	public void modifierRetour(int id, int idEmpru, LocalDate dateRetourAct, int retou ) throws RetourNotFoundException{	
		Retour e=rechercherParId(id);
		if(e==null)
			throw new RetourNotFoundException("Le retour n'existe pas");
		else {
			
			e.setIdEmprunt(idEmpru);
			e.setDateRetourActuelle(dateRetourAct);
			e.setRetard(retou);
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	public Retour rechercherParId(int id) {
		
		
		Optional<Retour> e=listeRetour.stream().filter(t->t.getId()==id).findFirst();
		if(e.isPresent()) {
			return e.get();
		}
		else {
			return null;
		}
		
		
	}
	
	
	public void listerRetours() {
		
		System.out.println(listeRetour.toString());	
		
	}
	
	
	
	
	
	
	
	
	public void trierListeRetourParId() {
		listeRetour.sort((o1,o2)->Integer.compare(o1.getId(), o2.getId()));
	}
	
	
	
	
	public void trierListeRetourParIdEmprunt() {
		listeRetour.sort((o1,o2)->Integer.compare(o1.getIdEmprunt(), o2.getIdEmprunt()));
	}
	
	
	public void trierListeRetourParDateRetourActuelle() {
		listeRetour.sort((o1,o2)->o1.getDateRetourActuelle().compareTo(o2.getDateRetourActuelle()));
	}
	
	public void trierListeRetoursParRetard() {
		listeRetour.sort((o1,o2)->Integer.compare(o1.getRetard(), o2.getRetard()));
	}
	

	
	
	
	public void sauvegarderRetoursCSV() {
		try {
	        File file = new File(filename);
			BufferedWriter bw= new BufferedWriter(new FileWriter(filename));
			bw.write("id;id Emprunt;date Retour Actuelle;Retard");
			for(int i=0;i<listeRetour.size();i++) {
				Retour e1=listeRetour.get(i);
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
	
	
	
	
	public void lireRetoursCSV() {
		try {
			BufferedReader br =new BufferedReader(new FileReader(filename));
			br.readLine();
			String line;
			while((line=br.readLine())!=null) {
				String[] words= line.split(";");
				int id=Integer.parseInt(words[0]);
				int idEmprunt=Integer.parseInt(words[1]);
				LocalDate dateRetourActuelle = LocalDate.parse(words[2]);
				int retard = Integer.parseInt(words[3]);
				
				Retour e=new Retour();
				e.setId(id);
				e.setIdEmprunt(idEmprunt);
				e.setDateRetourActuelle(dateRetourActuelle);
				e.setRetard(retard);
				
				listeRetour.add(e);
				
			}
			
			br.close();
		} catch (IOException e) {
			System.err.println("le fichier n'existe pas!");
		}
		
		
	}
	
	
	
	
	public void viderListe() {
		listeRetour.clear();

	}
	
	
	public int genererIdUnique() {
        if (listeRetour.isEmpty()) {
            return 1;
        } else {
            return listeRetour.stream()
                    .mapToInt(Retour::getId)
                    .max()
                    .getAsInt() + 1;
        }
    }
	

	
	
	public List<Retour> getListeRetour() {
	    return listeRetour;
	}
	
	
	


}
