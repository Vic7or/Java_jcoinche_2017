import java.util.ArrayList;
import java.util.Collections;

public class GameManager extends Thread {
    private Arbiter         arbiter = new Arbiter();
    private ArrayList<Card> stack = new ArrayList<Card>();
    private Team[]          teams = new Team[2];
    private Deck            deck = new Deck();
    private boolean         alive = true;
    private JcoincheServer  server;
    GameManager(JcoincheServer Server) {
        server = Server;
    }

    public void run() {
        System.out.println("GameManager is running.");
        makeTeams();
        deck.distrib(teams);
    }
    private void makeTeams() {
        Collections.shuffle(server.clientList);
        teams[0] = new Team(server.clientList.get(0), server.clientList.get(1));
        teams[1] = new Team(server.clientList.get(2), server.clientList.get(3));
        System.out.println(teams[0].getTeamName()+" versus "+teams[1].getTeamName());
    }
}
