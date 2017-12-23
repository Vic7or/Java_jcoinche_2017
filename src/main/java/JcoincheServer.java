import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class JcoincheServer {
    private static class ServerHolder {
        private final static JcoincheServer instance = new JcoincheServer();
    }
    private static class GameManager extends Thread {
        private static class GMHolder {
            private final static GameManager instance = new GameManager();
        }
        private Arbiter arbitre = Arbiter.getInstance();
        private ArrayList<Card> stack = new ArrayList<Card>();
        private Team[] teams = new Team[2];
        public static GameManager getInstance() {
            return GMHolder.instance;
        }
        private GameManager() {
            System.out.println("GameManager instantiated.");
        }
        @Override
        public void run(){
            System.out.println("GameManager is running.");
            makeTeams();
        }
        private void makeTeams() {

        }
    }

    public static class JClient extends Connection {
        public String           name;
        public Hand             hand;
        public ArrayList<Card>  stock;
        public int              points;
    }
    private final static Server         kryonet = new Server() {
        @Override
        protected Connection newConnection(){
            return new JClient();
    }};
    private final static List<JClient>   clientList = new ArrayList<JClient>();
    private final static GameManager    gameManager = GameManager.getInstance();
    public static JcoincheServer getInstance() {
        return ServerHolder.instance;
    }
    private JcoincheServer() {
        System.out.println("JcoincheServer Instantiated.");
    }
    public void run() throws java.io.IOException {
        JcoincheServer.kryonet.start();
        JcoincheServer.kryonet.bind(Network.ServerPort);
        Network.register(JcoincheServer.kryonet);
        JcoincheServer.kryonet.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                if (JcoincheServer.clientList.size() < 4) {
                    JcoincheServer.clientList.add((JClient) connection);
                    JcoincheServer.kryonet.sendToTCP(connection.getID(), new Packet(Network.Protocol.ASKNAME, null));
                    JcoincheServer.kryonet.sendToAllTCP(new Packet(Network.Protocol.WAITFORPLAYERS, String.valueOf(JcoincheServer.clientList.size()) + "/4"));
                    if (JcoincheServer.clientList.size() == 4)
                    {
                        JcoincheServer.kryonet.sendToAllTCP(new Packet(Network.Protocol.READY, null));
                        JcoincheServer.gameManager.start();
                    }
                }
                else {
                    JcoincheServer.kryonet.sendToTCP(connection.getID(), "Your connection has been rejected - Jcoinche server is full.");
                    connection.close();
                }

            }
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof String)
                {
                    System.out.println(object);
                }
                else if (object instanceof Packet)
                {
                    Packet packet = (Packet)object;
                    switch (packet.getType())
                    {
                        case NAME:
                            JClient c;
                            for (ListIterator<JClient> it = clientList.listIterator(); it.hasNext();)
                            {
                                if ((c = it.next()).getID() == connection.getID()) {
                                    c.name = (String) packet.getData();
                                    System.out.println("new client registered : "+ c.name);
                                }
                            }
                            break;
                        default:
                            System.out.println("Error: protocol unknows - command ignored.");
                            break;
                    }
                }
            }
            @Override
            public void disconnected(Connection connection){
                JClient c;
                for (ListIterator<JClient> it = clientList.listIterator(); it.hasNext();)
                {
                    if ((c = it.next()).getID() == connection.getID())
                    {
                        it.remove();
                        System.out.println(c.name + " has left !");
                        if (JcoincheServer.clientList.size() > 1)
                        {
                            JcoincheServer.kryonet.sendToAllTCP(new Packet(Network.Protocol.LEAVER,c.name+" has left - Waiting for players: "+ clientList.size() +"/4..."));
                        }
                    }
                }
                if (JcoincheServer.clientList.size() == 0)
                    JcoincheServer.kryonet.stop();
            }
        });
    }
}
