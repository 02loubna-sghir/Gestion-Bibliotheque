package models;

import java.time.LocalDate;
import java.util.Objects;

public class Emprunt {

	private int id;
	private int idUtilisateur;
	private String titreLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	private static int cmpt;

	public Emprunt() {
		
		cmpt++;
		this.id=cmpt;
		
	}

	public Emprunt(int idUtilisateur,String titreLivre, LocalDate dateEmprunt, LocalDate dateRetour) {
		super();
		cmpt++;
		this.id=cmpt;
		this.idUtilisateur = idUtilisateur;
		this.titreLivre = titreLivre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	
	
	@Override
	public String toString() {
		return  id + ";" + idUtilisateur + ";" + titreLivre + ";" + dateEmprunt + ";" + dateRetour;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	
	
	
	
	



	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getTitreLivre() {
		return titreLivre;
	}

	public void setTitreLivre(String titreLivre) {
		this.titreLivre = titreLivre;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateEmprunt, dateRetour, idUtilisateur, titreLivre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprunt other = (Emprunt) obj;
		return Objects.equals(dateEmprunt, other.dateEmprunt) && Objects.equals(dateRetour, other.dateRetour)
				&& idUtilisateur == other.idUtilisateur && Objects.equals(titreLivre, other.titreLivre);
	}


	

	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
/*
	public boolean equals(Emprunt obj) {

		if (this.id==obj.id && this.utili.equals(obj.utili) && this.dateEmprunt.isEqual(obj.dateEmprunt) 
				&& this.dateRetour.isEqual(obj.dateRetour) ) {
			return true;
		}
		
		else {
			return false;
		}
		
		
	}
	
*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
