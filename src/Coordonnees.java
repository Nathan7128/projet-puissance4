public class Coordonnees {
//    Attributs protégés
    int i = 0;
    int j = 0;

//    Méthodes publiques
    public Coordonnees() {}

    public Coordonnees(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
