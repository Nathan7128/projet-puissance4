import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Coordonnees)) {
            return false;
        }
        else {
            return this.i == ((Coordonnees)o).getI() && this.j == ((Coordonnees)o).getJ();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
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
