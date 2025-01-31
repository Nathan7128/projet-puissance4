import java.util.HashMap;

public class Grille {
//    Attributs protégés
    int largeur;
    int hauteur;
    HashMap<Coordonnees, Case> cases;

//    Méthodes publiques
    public Grille(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        cases = new HashMap<>();

        Coordonnees coord_temp;
        CaseVide case_vide_temp;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                coord_temp = new Coordonnees(i, j);
                case_vide_temp = new CaseVide(coord_temp);
                cases.put(coord_temp, case_vide_temp);
            }
        }
    }

    String afficher() {
//        Affichage bord du haut
        String affichage = "-----";
        for (int i = 0; i < largeur; i++) {
            affichage += "0" + i + "--";
        }
        affichage += "-\n";

//        Affichage des lignes de la grille
        Coordonnees coord_temp = new Coordonnees(); /* Coordonnées (i, j) de la case courante */
        for (int i = 0; i < hauteur; i++) {
            coord_temp.setI(i);
            affichage += " 0" + i + "| "; /* Affichage du bord gauche */

//            Affichage des cases de la ligne
            for (int j = 0; j < largeur; j++) {
                coord_temp.setJ(j);
                affichage += cases.get(coord_temp).afficher() + "  ";
            }

            affichage += "|\n"; /* Affichage du bord droit */
        }

//        Affichage du bord bas
        affichage += "--".repeat(3) + "----".repeat(largeur);

        return affichage;
    }

    void setCase(int i, int j, Case c) {
        Coordonnees coord = new Coordonnees(i, j);
        cases.remove(coord);
        cases.put(coord, c);
    }

    Case getCase(int i, int j) {
        Coordonnees coord = new Coordonnees(i, j);
        return cases.get(coord);
    }

    boolean pleine() {
        boolean pleine = true;
        Coordonnees coord_temp = new Coordonnees();
        Case case_temp;
        int i = 0, j = 0;
        while (i < hauteur && pleine) {
            coord_temp.setI(i);
            while (j < largeur && pleine) {
                coord_temp.setJ(j);
                case_temp = cases.get(coord_temp);
                pleine = case_temp instanceof CaseJaune ||  case_temp instanceof CaseRouge;
                j++;
            }
            i++;
        }
        return pleine;
    }

}
