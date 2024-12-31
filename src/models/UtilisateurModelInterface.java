package models;
import java.time.LocalDate;

import exceptions.*;
public interface UtilisateurModelInterface  {
	public void ajouterUtilisateur(Utilisateur u) throws UtilisateurExsiste;
	public void modifierUtilisateur(int id, String nouveauNom, String nouveauPrenom,String nouveauDroitAcces,String nouveauPassword,int nouveaunum,String nouveauEmail,LocalDate nouveauDate) throws UtilisateurNotFoundException;
	public void supprimerUtilisateur(int id) throws UtilisateurNotFoundException;
	public void trierListeUtilisateurs();
	public Utilisateur rechercherParID(int id);
	public void listerUtilisateurs();
	public void sauvegarderCSV();
	public void lireCSV();
}
