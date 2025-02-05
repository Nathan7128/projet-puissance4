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
        this.nbPionsAAligner = nbPionsAAligner;
        this.g = new Grille(largeur, hauteur);
    }

    public String afficher() {
        return g.afficher();
    }

    public boolean ligneDroite(int i, int j) {
        int compteur = 1;
        boolean bool_joueur1, bool_joueur2; /* Permet de savoir si la case qu'on regarde est une instance de la classe
                                               de la case associée au joueur en question */
        while (j + compteur < largeur && compteur < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(i, j + compteur) instanceof CaseJaune);
            bool_joueur2 = (joueur == 2) && !(g.getCase(i, j + compteur) instanceof CaseRouge);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            compteur++;
        }
        return compteur >= nbPionsAAligner;
    }

    public boolean ligneBas(int i, int j) {
        int compteur = 1;
        boolean bool_joueur1, bool_joueur2;
        while (i + compteur < hauteur && compteur < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(i + compteur, j) instanceof CaseJaune);
            bool_joueur2 = (joueur == 2) && !(g.getCase(i + compteur, j) instanceof CaseRouge);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            compteur++;
        }
        return compteur >= nbPionsAAligner;
    }

    public boolean ligneBasDroite(int i, int j) {
        int compteur = 1;
        boolean bool_joueur1, bool_joueur2;
        while (compteur + i < hauteur && compteur + j < largeur && compteur < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(compteur + i, compteur + j) instanceof CaseJaune);
            bool_joueur2 = (joueur == 2) && !(g.getCase(compteur + i, compteur + j) instanceof CaseRouge);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            compteur++;
        }
        return compteur >= nbPionsAAligner;
    }

    public boolean ligneBasGauche(int i, int j) {
        int compteur = 1;
        boolean bool_joueur1, bool_joueur2;
        while (compteur + i < hauteur && j - compteur >= 0 && compteur < nbPionsAAligner) {
            bool_joueur1 = (joueur == 1) && !(g.getCase(compteur + i, j - compteur) instanceof CaseJaune);
            bool_joueur2 = (joueur == 2) && !(g.getCase(compteur + i, j - compteur) instanceof CaseRouge);
            if (bool_joueur1 || bool_joueur2) {
                return false;
            }
            compteur++;
        }
        return compteur >= nbPionsAAligner;
    }

    public boolean gagne() {
        boolean bool_gagne = false, case_vide, bool_joueur1, bool_joueur2;
        int i = 0, j;
        while (i < hauteur && !bool_gagne) {
            j = 0;
            while (j < largeur && !bool_gagne) {
//                On regarde si la case itérée est une case vide (donc inutile de regarder cette case)
                case_vide = g.getCase(i, j) instanceof CaseVide;

//                On regarde si c'est au joueur 1 de jouer et si la case itérée est bien une case jaune
//                En effet, il est inutile de vérifier si le joueur a gagné dans le cas ou la case itérée n'est pas
//                de la couleur qu'il joue
                bool_joueur1 = joueur == 1 && g.getCase(i, j) instanceof CaseJaune;
//                De même pour le joueur 2
                bool_joueur2 = joueur == 2 && g.getCase(i, j) instanceof CaseRouge;

                if (!(case_vide) && (bool_joueur1 || bool_joueur2)) {
                    bool_gagne = ligneDroite(i, j) || ligneBas(i, j) || ligneBasDroite(i, j) || ligneBasGauche(i, j);
                }
                j++;
            }
            i++;
        }
        return bool_gagne;
    }

    public boolean partieFinie() {
        return gagne() || g.pleine();
    }

    public boolean coupPossible(int colonne) {
        return g.getCase(0, colonne) instanceof CaseVide;
    }

    public void jouer(int colonne) {
        Case new_case;
        int i = 1; /* on peut commencer à regarder la deuxième ligne de la colonne en question car le coup est possible
                      donc la ligne du haut est forcément une case vide */
        while (i < hauteur && g.getCase(i, colonne) instanceof CaseVide) {
            i++;
        }
        i--;
        Coordonnees new_coord = new Coordonnees(i, colonne);
        if (joueur == 1) {
            new_case = new CaseJaune(new_coord);
        }
        else {
            new_case = new CaseRouge(new_coord);
        }
        g.setCase(i, colonne, new_case);
    }

    public int getJoueur() {
        return joueur;
    }

    public void changerJoueur() {
        joueur = 3 - joueur;
    }
}
