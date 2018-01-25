public class Team {
    private JClient[]   players = new JClient[2];
    private int         points;
    private int         gamble;
    Team(JClient first, JClient second){
        players[0] = first;
        players[1] = second;
        points = 0;
        gamble = 0;
    }

    public int getGamble() {
        return gamble;
    }

    public int getPoints() {
        return points;
    }

    public JClient[] getPlayers() {
        return players;
    }
    public JClient getFirst() {
        return players[0];
    }
    public JClient getSecond() {
        return players[1];
    }

    public void setGamble(int gamble) {
        this.gamble = gamble;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public String getTeamName() {
        return "TEAM ["+players[0].name+"/"+players[1].name+"]";
    }
}
