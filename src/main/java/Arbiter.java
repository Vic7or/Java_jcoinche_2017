import java.util.ArrayList;
import java.util.HashMap;

//base en singleton pour Arbiter (Victor)
public class Arbiter {
    private static class ArbiterHolder {
        private final static Arbiter instance = new Arbiter();
    }
    private Card.Color Overlord = null;
    private HashMap Atout = new HashMap<Card.Value, Integer>();
    private HashMap SansAtout = new HashMap<Card.Value, Integer>();
    private Arbiter() {
        this.Atout.put(Card.Value.JACK, 20);
        this.Atout.put(Card.Value.NINE, 14);
        this.Atout.put(Card.Value.ACE, 11);
        this.Atout.put(Card.Value.TEN, 10);
        this.Atout.put(Card.Value.KING, 4);
        this.Atout.put(Card.Value.QUEEN, 3);
        this.Atout.put(Card.Value.EIGHT, 0);
        this.Atout.put(Card.Value.SEVEN, 0);
        this.SansAtout.put(Card.Value.ACE, 11);
        this.SansAtout.put(Card.Value.TEN, 10);
        this.SansAtout.put(Card.Value.KING, 4);
        this.SansAtout.put(Card.Value.QUEEN, 3);
        this.SansAtout.put(Card.Value.JACK, 2);
        this.SansAtout.put(Card.Value.NINE, 0);
        this.SansAtout.put(Card.Value.EIGHT, 0);
        this.SansAtout.put(Card.Value.SEVEN, 0);
    }
    private int getValueAtout(Card card){
        return (int) Atout.get(card.getValue());
    }
    private int getValueSansAtout(Card card) {
        return (int) SansAtout.get(card.getValue());
    }
    private boolean compareCards(Card played, Card stack) {
        if (played.getColor() == Overlord && stack.getColor() != Overlord)
            return true;
        else if (played.getColor() != Overlord && stack.getColor() == Overlord)
            return false;
        else
        {
            if (played.getColor() == Overlord && stack.getColor() == Overlord)
                return (getValueAtout(played) > getValueAtout(stack));
            else if (played.getColor() != Overlord && stack.getColor() != Overlord)
            {
                boolean color = (played.getColor() == stack.getColor());
                boolean value = (getValueSansAtout(played) > getValueSansAtout(stack));
                boolean ret = (color == value);
                return ret;
            }
        }
        return false;
    }
    public static Arbiter getInstance () {
        return ArbiterHolder.instance;
    }
    public Card.Color getOverlord() {
        return Overlord;
    }
    public void setOverlord(Card.Color overlord) {
        Overlord = overlord;
    }
    public boolean eval(ArrayList<Card> stack, JcoincheServer.JClient currentPlayer, int played)
    {

        return true;
    }
}
