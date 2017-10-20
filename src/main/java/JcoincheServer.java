import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class JcoincheServer {
    private static class ServerHolder {
        private final static JcoincheServer instance = new JcoincheServer();
    }
    public static class Client extends Connection {
        public String   name;
    }
    private final static Server         kryonet = new Server() {
        @Override
        protected Connection newConnection(){
            return new Client();
    }};
    private final static List<Client> clientList = new ArrayList<Client>();
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
                    System.out.println("new client connected ! =)");
                    JcoincheServer.clientList.add((Client) connection);
                    JcoincheServer.kryonet.sendToAllTCP("Waiting for all players: " + String.valueOf(JcoincheServer.clientList.size()) + "/4...");
                    if (JcoincheServer.clientList.size() == 4)
                        JcoincheServer.kryonet.sendToAllTCP("Everyone is ready, the game starts!");
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
            }
            @Override
            public void disconnected(Connection connection){
                System.out.println("a dummy client has left :( .");
                for (ListIterator<Client> it = clientList.listIterator(); it.hasNext();)
                {
                    if (it.next().getID() == connection.getID())
                       it.remove();
                }
                if (JcoincheServer.clientList.size() == 0)
                    JcoincheServer.kryonet.stop();
                else if (JcoincheServer.clientList.size() < 4)
                    JcoincheServer.kryonet.sendToAllTCP("Someone has Disconnected - Waiting for all players: " + String.valueOf(JcoincheServer.clientList.size()) + "/4...");
            }
        });
    }
}
