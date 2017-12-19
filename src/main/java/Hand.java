import java.util.ArrayList;
import java.util.ListIterator;

public class Hand {
    ArrayList<Card> cards;
    Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public Card play(int index) { return (cards.remove(index));
    }
    public void show() {
        Card card;
        int i = 0;
        for (ListIterator<Card> it = cards.listIterator();it.hasNext();){
            card = it.next();
            ++i;
            System.out.println("["+i+"] : "+card.getValue().toString()+" "+card.getColor().toString());
        }
    }
}
