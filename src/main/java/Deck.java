import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    Deck() {
        createDeck();
    } // Constructeur
    private ArrayList<Card>         cardList;
    private void createDeck() {
        cardList = new ArrayList<Card>();
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
    public void          distrib(Team[] teams){
        ArrayList<Hand> hands = new ArrayList<Hand>();
        while (hands.size() < 4)
        {
            ArrayList<Card> draw = new ArrayList<Card>();
            while (draw.size() < 8)
                draw.add(cardList.remove(0));
            hands.add(new Hand(draw));
        }
        teams[0].getFirst().hand = hands.get(0);
        teams[0].getSecond().hand = hands.get(1);
        teams[1].getFirst().hand = hands.get(2);
        teams[1].getSecond().hand = hands.get(3);
        return;
    }
    public void reset (){
        createDeck();
    }
}
