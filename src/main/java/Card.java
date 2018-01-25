public class Card {
    public enum Color {
        CLUB("CLUB"),
        SPADE("SPADE"),
        HEART("HEART"),
        DIAMOND("DIAMOND");
        private String color;
        Color(String id){
            this.color = id;
        }
    }
    public enum Value {
        SEVEN("SEVEN"),
        EIGHT("EIGHT"),
        NINE("NINE"),
        TEN("TEN"),
        JACK("JACK"),
        QUEEN("QUEEN"),
        KING("KING"),
        ACE("ACE");
        public String type;
        Value(String id){
            this.type = id;
        }
    }
    private Color color;
    private Value value;
    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }
    public Color getColor() {
        return color;
    }
    public Value getValue() {
        return value;
    }
}
