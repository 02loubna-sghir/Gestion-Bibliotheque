import controllers.MainController;
import exceptions.LivreNotFoundException;

public class Main {
    public static void main(String[] args) {
        // Gérer l'exception potentielle de la création du contrôleur
        try {
            MainController controller = new MainController();
        } catch (LivreNotFoundException e) {
            // Gérer l'exception (afficher un message d'erreur, par exemple)
            e.printStackTrace();
            System.out.println("Erreur: Livre non trouvé");
        }
    }
}