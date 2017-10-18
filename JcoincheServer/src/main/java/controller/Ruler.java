package controller;

public class Ruler {
    private static class SingletonHolder {
        private final static Ruler instance = new Ruler();
    }
    public static Ruler getInstance() {
        return SingletonHolder.instance;
    }
    private Ruler() {}
}
