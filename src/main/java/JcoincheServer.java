import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.*;

public class JcoincheServer {
    private GameManager gameManager = new GameManager(this);
    public ArrayList<JClient> clientList = new ArrayList<JClient>();
    private JcoincheServer ref = this;
    private Server kryonet = new Server() {
        @Override
        protected Connection newConnection() {
            return new JClient();
        }
    };
    JcoincheServer() {System.out.println("JcoincheServer Instanciated");}
    public void run() throws java.io.IOException {
        kryonet.start();
        kryonet.bind(Network.ServerPort);
        Network.register(kryonet);
        kryonet.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                if (clientList.size() < 4) {
                    clientList.add((JClient) connection);
                    kryonet.sendToTCP(connection.getID(), new Packet(Network.Protocol.ASKNAME, null));
                    kryonet.sendToAllTCP(new Packet(Network.Protocol.WAITFORPLAYERS, String.valueOf(clientList.size()) + "/4"));
                    /*if (JcoincheServer.clientList.size() == 4)
                    {
                        JcoincheServer.kryonet.sendToAllTCP(new Packet(Network.Protocol.READY, null));
                        JcoincheServer.gameManager.start();
                    }*/
                }
                else {
                    kryonet.sendToTCP(connection.getID(), "Your connection has been rejected - Jcoinche server is full.");
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
                            if (clientList.size() == 4)
                            {
                                synchronized (ref) {
                                    ref.notify();
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
                        if (clientList.size() > 1)
                        {
                            kryonet.sendToAllTCP(new Packet(Network.Protocol.LEAVER,c.name+" has left - Waiting for players: "+ clientList.size() +"/4..."));
                        }
                    }
                }
                if (clientList.size() == 0)
                    kryonet.stop();
            }
        });
        synchronized (ref) {
            try {
                System.out.println("Waiting all players to start the game...");
                ref.wait();
                kryonet.sendToAllTCP(new Packet(Network.Protocol.READY, null));
                gameManager.start();
            } catch (InterruptedException e) {
                System.out.println("CATCH BLOCK : "+e.getMessage());
            }
        }
    }
}
