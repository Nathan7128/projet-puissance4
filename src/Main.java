import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Partie partie = new Partie();
        int colonne;
        Scanner lectureColonne = new Scanner(System.in);
        partie.afficher();

        while (!partie.partieFinie()) {
            System.out.println("Colonne : ");
            colonne = lectureColonne.nextInt();
            partie.jouer(colonne);
            partie.afficher();
        }
    }
}