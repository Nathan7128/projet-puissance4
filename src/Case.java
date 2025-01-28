abstract class Case {
//    Attributs protégés
    Coordonnees p;

//    Méthodes publiques
    public Case(Coordonnees coord) {
        p = coord;
    }

    public int getI() {
        return p.getI();
    }

    public int getJ() {
        return p.getJ();
    }

    abstract public String afficher();
}
