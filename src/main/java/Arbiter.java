import java.util.HashMap;

//base en singleton pour Arbiter (Victor)
public class Arbiter {
    private static class ArbiterHolder {
        private final static Arbiter instance = new Arbiter();
    }
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
    public static Arbiter getInstance () {
        return ArbiterHolder.instance;
    }
}
