package models;

import exceptions.*;


import java.io.*;
import java.time.LocalDate;
import java.util.List;

public interface IEmprunt {


	public void prolongerDateRetour(Emprunt empru, LocalDate nouvelledate) throws DateInacceptableException;
	public void ajouterEmprunt(Emprunt emp) throws EmpruntExisteException;
	public Emprunt rechercherParId(int i);
	public void supprimerEmprunt(int id) throws EmpruntNotFoundException;
	public void modifierEmprunt(int id, int idutili, String titrelivr, LocalDate de, LocalDate dr ) throws EmpruntNotFoundException;
	public void listerEmprunts();
	public void trierListeEmpruntsParDateEmprunt();
	public void trierListeEmpruntsParDateRetour();
	public void sauvegarderEmpruntsCSV();
	public void lireEmpruntsCSV();
	
}
