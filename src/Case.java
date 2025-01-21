abstract class Case {
//    Attributs protégés
    Coordonnees p;

//    Méthodes publiques
    public Case(int i, int j) {
        p = new Coordonnees(i, j);
    }

    public int getI() {
        return p.getI();
    }

    public int getJ() {
        return p.getJ();
    }

    abstract public String afficher();
}
