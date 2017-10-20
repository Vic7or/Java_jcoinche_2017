package controller;

public class Ruler {
    private static class RulerHolder {
        private final static Ruler instance = new Ruler();
    }
    public static Ruler getInstance() {
        return RulerHolder.instance;
    }
    private Ruler() {}
}
