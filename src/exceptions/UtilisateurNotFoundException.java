package exceptions;

public class UtilisateurNotFoundException extends Exception {

	public UtilisateurNotFoundException(int id) {
		// TODO Auto-generated constructor stub
		super("L'utilisateur avec l'id :"+id+"n'exsiste pas");
	}
	public UtilisateurNotFoundException(String message) {
		super(message);
	}
}
