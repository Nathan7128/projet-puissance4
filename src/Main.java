import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Partie partie = new Partie();
        int colonne;
        Scanner scanner = new Scanner(System.in);
        System.out.println(partie.afficher());

        while (!partie.partieFinie()) {
            System.out.print("Colonne : ");
            colonne = scanner.nextInt();
            while (!partie.coupPossible(colonne)) {
                System.out.print("Coup impossible, veuillez saisir une colonne valide : ");
                colonne = scanner.nextInt();
            }
            partie.jouer(colonne);
            System.out.println(partie.afficher());
        }
    }
}