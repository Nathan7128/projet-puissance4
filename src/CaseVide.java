public class CaseVide extends Case {
//    Méthodes publiques
    public CaseVide(Coordonnees coord) {
        super(coord);
    }

    @Override
    public String afficher() {
        return "  ";
    }
}
