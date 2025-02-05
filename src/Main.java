import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Partie partie;
        int colonne, largeur, hauteur, nbPionsAAligner;
        String recommencer_partie;
        boolean partie_finie, jouer = true;
        Scanner scanner = new Scanner(System.in);

        while (jouer) {
//            Instanciation de la partie en demandant à l'utilisateur la taille de la grille et le nombre de pions à
//            aligner pour gagner
            do {
                System.out.print("Largeur : ");
                largeur = scanner.nextInt();
            } while (largeur < 1);
            do {
                System.out.print("Hauteur : ");
                hauteur = scanner.nextInt();
            } while (hauteur < 1);

            do {
                System.out.print("Nombre de pions à aligner pour gagner : ");
                nbPionsAAligner = scanner.nextInt();
                System.out.println(Math.min(largeur, hauteur));
            } while (nbPionsAAligner > Math.min(largeur, hauteur));

            partie = new Partie(largeur, hauteur, nbPionsAAligner);

//            Affichage de la grille vide
            System.out.println(partie.afficher());

//            Déroulement de la partie
            partie_finie = false;
            while (!partie_finie) {
                System.out.print("Colonne : ");
                colonne = scanner.nextInt();
                while (!partie.coupPossible(colonne)) {
                    System.out.print("Coup impossible, veuillez saisir une colonne valide : ");
                    colonne = scanner.nextInt();
                }
                partie.jouer(colonne);

                partie_finie = partie.partieFinie();
                if (!partie_finie) {
                    partie.changerJoueur(); /* Changement de joueur après avoir vérifié si la partie était finie */
                }

                System.out.println(partie.afficher());
            }

//            Affichage du message de fin de partie ("Bravo" ou "Match nul")
            if (partie.gagne()) {
                System.out.println("Bravo joueur " + partie.getJoueur() + ", tu as gagné !");
            }
            else {
                System.out.println("Match nul !");
            }

//            On demande à l'utilisateur s'il souhaite recommencer une partie
            do {
                System.out.println("Recommencer une partie ? (o/n)");
                recommencer_partie = scanner.next();
            } while (!recommencer_partie.equals("o") && !recommencer_partie.equals("n"));

            if (recommencer_partie.equals("n")) {
                jouer = false;
            }
        }
    }
}