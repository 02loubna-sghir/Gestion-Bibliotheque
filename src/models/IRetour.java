package models;

import java.time.LocalDate;

import exceptions.RetourExisteException;
import exceptions.RetourNotFoundException;

public interface IRetour {
	
	public void ajouterRetour(Retour retou) throws RetourExisteException;
	public void supprimerRetour(int id) throws RetourNotFoundException;
	public void modifierRetour(int id, int idEmpru, LocalDate dateRetourAct, int retou ) throws RetourNotFoundException;
	public Retour rechercherParId(int id);
	public void listerRetours();
	public void trierListeRetourParId();
	public void trierListeRetourParIdEmprunt();
	public void trierListeRetourParDateRetourActuelle();
	public void trierListeRetoursParRetard();
	public void sauvegarderRetoursCSV();
	public void lireRetoursCSV();
	

}