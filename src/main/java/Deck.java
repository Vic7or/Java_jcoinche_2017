import java.util.List;

// Base en singleton pour le deck (Giacomo et Bastien) a rajouter dans le game manager du server qd c'est fini.
public class Deck {
    private static class DeckHolder {
        private final static Deck instance = new Deck();
    }
    private Deck() {} // Constructeur
    public static Deck getInstance() {
        // Pour utiliser la classe :  Deck myDeck = Deck.getInstance();
        return DeckHolder.instance;
    }
    // A feed avec une classe Card , liste de Card, méthodes pour distribuer etc...
}
