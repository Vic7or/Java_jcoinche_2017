import java.util.*;

// Base en singleton pour le deck (Giacomo et Bastien) a rajouter dans le game manager du server qd c'est fini.
public class Deck {
    private static class DeckHolder {
        private final static Deck instance = new Deck();
    }
    private Deck() {
        createDeck();
    } // Constructeur
    private void createDeck() {
        cardList = new ArrayList();
        cardList.add(new Card(Card.Color.CLUB, Card.Value.SEVEN));
        cardList.add(new Card(Card.Color.CLUB, Card.Value.EIGHT));
        cardList.add(new Card(Card.Color.CLUB, Card.Value.NINE));
        cardList.add(new Card(Card.Color.CLUB, Card.Value.TEN));
        cardList.add(new Card(Card.Color.CLUB, Card.Value.JACK));
        cardList.add(new Card(Card.Color.CLUB, Card.Value.QUEEN));
        cardList.add(new Card(Card.Color.CLUB, Card.Value.KING));
        cardList.add(new Card(Card.Color.CLUB, Card.Value.ACE));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.SEVEN));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.EIGHT));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.NINE));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.TEN));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.JACK));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.QUEEN));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.KING));
        cardList.add(new Card(Card.Color.DIAMOND, Card.Value.ACE));
        cardList.add(new Card(Card.Color.HEART, Card.Value.SEVEN));
        cardList.add(new Card(Card.Color.HEART, Card.Value.EIGHT));
        cardList.add(new Card(Card.Color.HEART, Card.Value.NINE));
        cardList.add(new Card(Card.Color.HEART, Card.Value.TEN));
        cardList.add(new Card(Card.Color.HEART, Card.Value.JACK));
        cardList.add(new Card(Card.Color.HEART, Card.Value.QUEEN));
        cardList.add(new Card(Card.Color.HEART, Card.Value.KING));
        cardList.add(new Card(Card.Color.HEART, Card.Value.ACE));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.SEVEN));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.EIGHT));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.NINE));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.TEN));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.JACK));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.QUEEN));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.KING));
        cardList.add(new Card(Card.Color.SPADE, Card.Value.ACE));
        Collections.shuffle(cardList);
    }
    public static Deck getInstance() {
        // Pour utiliser la classe :  Deck myDeck = Deck.getInstance();
        return DeckHolder.instance;
    }
    private ArrayList<Card>         cardList;
    public ArrayList<Hand>          distrib(){
        ArrayList<Hand> hands = new ArrayList();
        while (hands.size() < 4)
        {
            ArrayList<Card> draw = new ArrayList();
            while (draw.size() < 8)
                draw.add(cardList.remove(0));
            hands.add(new Hand(draw));
        }
        return hands;
    }
    public void reset (){
        createDeck();
    }
    // A feed avec une classe Card , liste de Card, méthodes pour distribuer etc...
}
