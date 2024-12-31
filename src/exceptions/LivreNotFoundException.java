package exceptions;

public class LivreNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Exception levée lorsqu'un livre avec un identifiant spécifique n'est pas trouvé.
     * @param id L'identifiant du livre introuvable.
     */
    public LivreNotFoundException(int id) {
       super("Le livre avec l'ID " + id + " n'existe pas!");
    }

    /**
     * Exception levée avec un message personnalisé.
     * @param message Le message d'erreur personnalisé.
     */
    public LivreNotFoundException(String message) {
        super(message);
    }
}