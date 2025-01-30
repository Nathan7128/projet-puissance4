public class Partie {
//    Attributs protégés
    int largeur = 7;
    int hauteur = 6;
    int nbPionsAAligner = 4;
    Grille g;
    int joueur = 1; /* Le joueur 1 correspond aux cases jaunes et le joueur 2 aux cases rouges */

//    Méthodes publiques
    public Partie() {
        this.g = new Grille(this.largeur, this.hauteur);
    }

    public Partie(int largeur, int hauteur, int nbPionsAAligner) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.nbPionsAAligner = nbPionsAAligner; /* Exception si nbPions <= 1 ou nbPions >= min(largeur, hauteur) !!!*/
        this.g = new Grille(largeur, hauteur);
    }

    public void afficher() {
        g.afficher();
    }

    public boolean ligneDroite(int i, int j) {
        int i_temp = i + 1;
        boolean bool_joueur1, bool_joueur2; /* Permet de savoir si la case qu'on regarde est une instance de la classe
                                               de la case associée au joueur en question */
        while (i_temp < hauteur && i_temp - i < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(i_temp, j) instanceof CaseRouge);
            bool_joueur2 = (joueur == 2) && !(g.getCase(i_temp, j) instanceof CaseJaune);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            i_temp++;
        }
        return i_temp - i + 1 >= nbPionsAAligner;
    }

    public boolean ligneBas(int i, int j) {
        int j_temp = j + 1;
        boolean bool_joueur1, bool_joueur2;
        while (j_temp < largeur && j_temp - j < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(i, j_temp) instanceof CaseRouge);
            bool_joueur2 = (joueur == 2) && !(g.getCase(i, j_temp) instanceof CaseJaune);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            j_temp++;
        }
        return j_temp - j + 1 >= nbPionsAAligner;
    }

    public boolean ligneBasDroite(int i, int j) {
        int i_temp = i + 1, j_temp = j + 1;
        boolean bool_joueur1, bool_joueur2;
        while (i_temp < hauteur && j_temp < largeur && j_temp - j < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(i_temp, j_temp) instanceof CaseRouge);
            bool_joueur2 = (joueur == 2) && !(g.getCase(i_temp, j_temp) instanceof CaseJaune);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            i_temp++;
            j_temp++;
        }
        return j_temp - j + 1 >= nbPionsAAligner;
    }

    public boolean ligneBasGauche(int i, int j) {
        int i_temp = i + 1, j_temp = j - 1;
        boolean bool_joueur1, bool_joueur2;
        while (i_temp < hauteur && j_temp >= 0 && i_temp - i < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(i_temp, j_temp) instanceof CaseRouge);
            bool_joueur2 = (joueur == 2) && !(g.getCase(i_temp, j_temp) instanceof CaseJaune);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            i_temp++;
            j_temp--;
        }
        return i_temp - i + 1 >= nbPionsAAligner;
    }

    public boolean gagne() {
        boolean gagne = false;
        int i = 0, j = 0;
        while (i < hauteur && !gagne) {
            while (j < largeur && !gagne) {
                gagne = ligneDroite(i, j) || ligneBas(i, j) || ligneBasDroite(i, j) || ligneBasGauche(i, j);
                i++;
                j++;
            }
        }
        return gagne;
    }

    public boolean partieFinie() {
        return gagne() || g.pleine();
    }

    public boolean coupPossible(int colonne) {
        return g.getCase(0, colonne) instanceof CaseVide;
    }

    public void jouer(int colonne) {
        if (coupPossible(colonne)) {
            Case new_case;
            int i = 1; /* on peut commencer à regarder la deuxième ligne de la colonne en question car le coup est possible
                          donc la ligne du haut est forcément une case vide */
            while (g.getCase(i, colonne) instanceof CaseVide) {
                i++;
            }
            Coordonnees new_coord = new Coordonnees(i, colonne);
            if (joueur == 1) {
                new_case = new CaseJaune(new_coord);
            }
            else {
                new_case = new CaseRouge(new_coord);
            }
            g.setCase(i, colonne, new_case);
            joueur = 3 - joueur;
        }
    }

    public int getJoueur() {
        return joueur;
    }

}
