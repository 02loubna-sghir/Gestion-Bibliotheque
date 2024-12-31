package models;

import java.time.LocalDate;
import java.util.Objects;

public class Retour {

	private int id;
	private int idEmprunt;
	private LocalDate dateRetourActuelle;
	private int retard;
	private static int cmpt;

	public Retour() {
		
		cmpt++;
		this.id=cmpt;
		
	}

	public Retour(int idEmprunt, LocalDate dateRetourActuelle, int retard) {
		super();
		cmpt++;
		this.id=cmpt;
		this.idEmprunt = idEmprunt;
		this.dateRetourActuelle = dateRetourActuelle;
		this.retard = retard;
	}
	

	
	
	
	
	
	
	

	@Override
	public String toString() {
		return id + ";" + idEmprunt + ";" +  dateRetourActuelle + ";" + retard;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmprunt() {
		return idEmprunt;
	}

	public void setIdEmprunt(int idEmprunt) {
		this.idEmprunt = idEmprunt;
	}

	public LocalDate getDateRetourActuelle() {
		return dateRetourActuelle;
	}

	public void setDateRetourActuelle(LocalDate dateRetourActuelle) {
		this.dateRetourActuelle = dateRetourActuelle;
	}

	public int getRetard() {
		return retard;
	}

	public void setRetard(int retard) {
		this.retard = retard;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateRetourActuelle, idEmprunt, retard);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Retour other = (Retour) obj;
		return Objects.equals(dateRetourActuelle, other.dateRetourActuelle) && idEmprunt == other.idEmprunt
				&& retard == other.retard;
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
