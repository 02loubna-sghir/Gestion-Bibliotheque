package exceptions;

public class UtilisateurExsiste extends Exception {

	public UtilisateurExsiste() {
		// TODO Auto-generated constructor stub
		super("L'utilisateur exsiste déja");
	}
	public UtilisateurExsiste(String message) {
		super(message);
	}
	
}
