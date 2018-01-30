import java.util.ArrayList;
import java.util.HashMap;

public class Arbiter {
    private Card.Color  Overlord = null;
    private HashMap<Card.Value, Integer> Atout = new HashMap<Card.Value, Integer>();
    private HashMap<Card.Value, Integer> SansAtout = new HashMap<Card.Value, Integer>();
    private HashMap<Card.Value, Integer> ScoreAtout = new HashMap<Card.Value, Integer>();
    private HashMap<Card.Value, Integer> ScoreSansAtout = new HashMap<Card.Value, Integer>();
    Arbiter() {

        Atout.put(Card.Value.JACK, 15);
        Atout.put(Card.Value.NINE, 14);
        Atout.put(Card.Value.ACE, 13);
        Atout.put(Card.Value.TEN, 12);
        Atout.put(Card.Value.KING, 11);
        Atout.put(Card.Value.QUEEN, 10);
        Atout.put(Card.Value.EIGHT, 9);
        Atout.put(Card.Value.SEVEN, 8);

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

    /*private boolean compareCards(Card played, Card stack) {
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
    } */

    public Card.Color getOverlord() {
        return Overlord;
    }

    public void setOverlord(Card.Color overlord)
    {
        Overlord = overlord;
    }



    private boolean check_power_hand(ArrayList<Card> stack, JClient currentPlayer)
    {
        int i = 0;
        int y = 0;
        int stronger = 0;
        boolean stop = false;

        while (currentPlayer.hand.getCards().get(i) != null)
        {
            while (stack.get(y) != null)
            {
                if (this.getValueAtout(currentPlayer.hand.getCards().get(i)) < this.getValueAtout(stack.get(y)))
                    stronger += 1;
                y += 1;
            }
            if (stronger == y)
                stop = true;
            else
                stronger = 0;
            i += 1;
        }
        return !stop;
    }

    private boolean check_power_card(ArrayList<Card> stack, Card currentCard, JClient currentPlayer) {
        int i = 0;
        int stronger = 0;

        while (stack.get(i) != null) {
            if (this.getValueAtout(currentCard) < this.getValueAtout(stack.get(i)))
                stronger += 1;
            i += 1;
        }

        return stronger >= i || check_power_hand(stack, currentPlayer);

    }

    private boolean check_atout_turn(ArrayList<Card> stack, Card currentCard, JClient currentPlayer) {
        return stack.get(0).getColor() != getOverlord() || check_power_card(stack, currentCard, currentPlayer);
    }

    private boolean check_hand_color(ArrayList<Card> stack, JClient currentPlayer)
    {
        int i = 0;
        boolean stop = false;

        while (i < currentPlayer.hand.getCards().size())
        {
            if (stack.get(0).getColor() == currentPlayer.hand.getCards().get(i).getColor())
            {
                stop = true;
            }
            i += 1;
        }

        return (!stop);
    }

   /* private boolean check_friendMaster(ArrayList<Card> stack, JClient currentPlayer) {
        switch (stack.size())
        {
            case 1:
                return check_atoutcard(stack, currentPlayer);
            case 2:
                check_strongercard(stack);
        }
    }*/

    //private boolean check_atoutcard(ArrayList<Card> stack, JClient currentPlayer) {
    //}

    private  boolean check_card_color(ArrayList<Card> stack, Card currentCard, JClient currentPlayer)
    {
        if (currentCard.getColor() == stack.get(0).getColor())
            return check_atout_turn(stack, currentCard, currentPlayer);
        else
            return check_hand_color(stack, currentPlayer);
    }

    private boolean check_card_stack(ArrayList<Card> stack, JClient currentPlayer, Card currentCard) {
        return stack.isEmpty() || check_card_color(stack, currentCard, currentPlayer);

    }

    public boolean eval(ArrayList<Card> stack, JClient currentPlayer, Team[] teams, int played)
    {
        Card    currentCard = currentPlayer.hand.cards.get(played);
        return(check_card_stack(stack, currentPlayer, currentCard));
    }
}
