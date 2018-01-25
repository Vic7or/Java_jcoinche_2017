import java.util.ArrayList;
import java.util.HashMap;

public class Arbiter {
    private Card.Color  Overlord = null;
    private HashMap<Card.Value, Integer> Atout = new HashMap<Card.Value, Integer>();
    private HashMap<Card.Value, Integer> SansAtout = new HashMap<Card.Value, Integer>();
    private HashMap<Card.Value, Integer> ScoreAtout = new HashMap<Card.Value, Integer>();
    private HashMap<Card.Value, Integer> ScoreSansAtout = new HashMap<Card.Value, Integer>();
    Arbiter() {

        Atout.put(Card.Value.JACK, 7);
        Atout.put(Card.Value.NINE, 6);
        Atout.put(Card.Value.ACE, 5);
        Atout.put(Card.Value.TEN, 4);
        Atout.put(Card.Value.KING, 3);
        Atout.put(Card.Value.QUEEN, 2);
        Atout.put(Card.Value.EIGHT, 1);
        Atout.put(Card.Value.SEVEN, 0);

        SansAtout.put(Card.Value.ACE, 7);
        SansAtout.put(Card.Value.TEN, 6);
        SansAtout.put(Card.Value.KING, 5);
        SansAtout.put(Card.Value.QUEEN, 4);
        SansAtout.put(Card.Value.JACK, 3);
        SansAtout.put(Card.Value.NINE, 2);
        SansAtout.put(Card.Value.EIGHT, 1);
        SansAtout.put(Card.Value.SEVEN, 0);

        ScoreAtout.put(Card.Value.JACK, 20);
        ScoreAtout.put(Card.Value.NINE, 14);
        ScoreAtout.put(Card.Value.ACE, 11);
        ScoreAtout.put(Card.Value.TEN, 10);
        ScoreAtout.put(Card.Value.KING, 4);
        ScoreAtout.put(Card.Value.QUEEN, 3);
        ScoreAtout.put(Card.Value.EIGHT, 0);
        ScoreAtout.put(Card.Value.SEVEN, 0);

        ScoreSansAtout.put(Card.Value.ACE, 11);
        ScoreSansAtout.put(Card.Value.TEN, 10);
        ScoreSansAtout.put(Card.Value.KING, 4);
        ScoreSansAtout.put(Card.Value.QUEEN, 3);
        ScoreSansAtout.put(Card.Value.JACK, 2);
        ScoreSansAtout.put(Card.Value.NINE, 0);
        ScoreSansAtout.put(Card.Value.EIGHT, 0);
        ScoreSansAtout.put(Card.Value.SEVEN, 0);
    }
    private int getValueAtout(Card card){
        return Atout.get(card.getValue());
    }
    private int getValueSansAtout(Card card) {
        return SansAtout.get(card.getValue());
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
                return (color == value);
            }
        }
        return false;
    }
    public Card.Color getOverlord() {
        return Overlord;
    }
    public void setOverlord(Card.Color overlord) {
        Overlord = overlord;
    }
    public boolean eval(ArrayList<Card> stack, JClient currentPlayer, int played)
    {
        return true;
    }
}
