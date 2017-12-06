//base en singleton pour Arbiter (Victor)
public class Arbiter {
    private static class ArbiterHolder {
        private final static Arbiter instance = new Arbiter();
    }
    private Arbiter() {}
    public static Arbiter getInstance () {
        return ArbiterHolder.instance;
    }
}
