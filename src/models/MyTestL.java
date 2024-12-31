//package models;
//
//import exceptions.LivreNotFoundException;
//import exceptions.LivreExisteException;
//
//public class MyTestL {
//
//    public static void main(String[] args) {
//        // Nom du fichier CSV
//        String fichierCSV = "livres.csv";
//
//        // Création du modèle avec le fichier CSV
//        ILivreImpl model = new ILivreImpl(fichierCSV);
//
//        // Création et ajout des livres
//        System.out.println("Ajout des livres initiaux...");
//        try {
//        	model.ajouterLivre(new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "Fiction", "9781234567890", "Gallimard", "Français", 7));
//            model.ajouterLivre(new Livre("1984", "George Orwell", 1949, "Science-fiction", "9789876543210", "Secker & Warburg", "Anglais", 2));
//            model.ajouterLivre(new Livre("Moby Dick", "Herman Melville", 1851, "Aventure", "9781593080000", "Harper & Brothers", "Anglais", 3));
//            model.ajouterLivre(new Livre("Hamlet", "William Shakespeare", 1603, "Théâtre", "9780451526922", "Simon & Schuster", "Anglais", 4));
//            model.ajouterLivre(new Livre("Les Misérables", "Victor Hugo", 1862, "Classique", "9780140444308", "Le Livre de Poche", "Français", 5));
//            model.ajouterLivre(new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", 1954, "Fantasy", "9780261102385", "Allen & Unwin", "Anglais", 8));
//            model.ajouterLivre(new Livre("Pride and Prejudice", "Jane Austen", 1813, "Romance", "9781503290563", "CreateSpace", "Anglais", 10));
//            model.ajouterLivre(new Livre("L'Étranger", "Albert Camus", 1942, "Philosophie", "9782070308024", "Gallimard", "Français", 6));
//            model.ajouterLivre(new Livre("Le Comte de Monte-Cristo", "Alexandre Dumas", 1844, "Aventure", "9782070413054", "Le Livre de Poche", "Français", 9));
//            model.ajouterLivre(new Livre("Dracula", "Bram Stoker", 1897, "Horreur", "9780141439846", "Penguin Classics", "Anglais", 1));
//            model.ajouterLivre(new Livre("Don Quichotte", "Miguel de Cervantes", 1605, "Classique", "9788491050560", "Ediciones Cátedra", "Espagnol", 12));
//            model.ajouterLivre(new Livre("War and Peace", "Leo Tolstoy", 1869, "Historique", "9781853260629", "Wordsworth Editions", "Anglais", 11));
//            model.ajouterLivre(new Livre("The Great Gatsby", "F. Scott Fitzgerald", 1925, "Romance", "9780743273565", "Scribner", "Anglais", 4));
//            model.ajouterLivre(new Livre("Anna Karenina", "Leo Tolstoy", 1877, "Classique", "9781853262715", "Wordsworth Editions", "Anglais", 6));
//            model.ajouterLivre(new Livre("Brave New World", "Aldous Huxley", 1932, "Science-fiction", "9780060850524", "Harper Perennial Modern Classics", "Anglais", 3));
//            model.ajouterLivre(new Livre("The Catcher in the Rye", "J.D. Salinger", 1951, "Classique", "9780316769488", "Little, Brown and Company", "Anglais", 8));
//            model.ajouterLivre(new Livre("To Kill a Mockingbird", "Harper Lee", 1960, "Drama", "9780060935467", "Harper Perennial Modern Classics", "Anglais", 7));
//            model.ajouterLivre(new Livre("Frankenstein", "Mary Shelley", 1818, "Horreur", "9780451532245", "Penguin Classics", "Anglais", 5));
//            model.ajouterLivre(new Livre("The Odyssey", "Homer", -800, "Classique", "9780140268867", "Penguin Classics", "Anglais", 13));
//            model.ajouterLivre(new Livre("The Picture of Dorian Gray", "Oscar Wilde", 1890, "Philosophie", "9780141439570", "Penguin Classics", "Anglais", 9));
//            model.ajouterLivre(new Livre("Les Fleurs du mal", "Charles Baudelaire", 1857, "Poésie", "9782070310898", "Gallimard", "Français", 6));
//            model.ajouterLivre(new Livre("La Peste", "Albert Camus", 1947, "Roman", "9782070311154", "Gallimard", "Français", 4));
//            model.ajouterLivre(new Livre("The Lord of the Flies", "William Golding", 1954, "Classique", "9780399501487", "Penguin Books", "Anglais", 3));
//            model.ajouterLivre(new Livre("Les Trois Mousquetaires", "Alexandre Dumas", 1844, "Aventure", "9782070409507", "Le Livre de Poche", "Français", 10));
//            model.ajouterLivre(new Livre("A Tale of Two Cities", "Charles Dickens", 1859, "Historique", "9781853262616", "Wordsworth Editions", "Anglais", 2));
//            model.ajouterLivre(new Livre("The Brothers Karamazov", "Fyodor Dostoevsky", 1880, "Philosophique", "9780374528379", "Farrar, Straus and Giroux", "Anglais", 4));
//            model.ajouterLivre(new Livre("The Divine Comedy", "Dante Alighieri", 1320, "Classique", "9780199535641", "Oxford University Press", "Italien", 7));
//
//         } catch (LivreExisteException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // Afficher tous les livres
//        System.out.println("\nListe initiale des livres :");
//        model.listerLivres();
//
//        // Sauvegarder les livres dans le fichier CSV
//        model.sauvegarderLivresCSV();
//        System.out.println("\nLivres sauvegardés dans le fichier CSV.");
//
//    }  
//}
package models;


