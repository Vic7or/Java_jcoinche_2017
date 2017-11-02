import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JcoincheClient {
    private static class ClientHolder {
        private final static JcoincheClient instance = new JcoincheClient();
    }
    final static Client         kryonet = new Client();
    private static String       name;
    public static JcoincheClient getInstance() {
        return ClientHolder.instance;
    }
    private JcoincheClient() {
        this.name = null;
        System.out.println("JcoincheClient instantiated.");
    }
    private void registerPlayer() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (JcoincheClient.name == null)
        {
            System.out.println("Welcome to Jcoinche, please type your player name:");
            if ((s = rd.readLine()) != null && s.length() != 0)
                JcoincheClient.name = s;
        }
    };
    public void run() throws IOException {
        JcoincheClient.kryonet.start();
        Network.register(JcoincheClient.kryonet);
        JcoincheClient.kryonet.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                JcoincheClient.kryonet.sendTCP(new String("hello server !"));
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
                        case ASKNAME:
                            JcoincheClient.kryonet.sendTCP(new Packet(Network.Protocol.NAME, JcoincheClient.name));
                            break;
                        case WAITFORPLAYERS:
                            System.out.println(packet.getTypeWhat() + packet.getData());
                            break;
                        case REJECTCLIENT:
                            System.out.println(packet.getTypeWhat());
                            break;
                        case LEAVER:
                            System.out.println(packet.getTypeWhat() + packet.getData());
                            break;
                        case READY:
                            System.out.println(packet.getTypeWhat());
                            break;
                        case NEWTURN:
                            break;
                        case ENDTURN:
                            break;
                        case ASKGAMBLE:
                            break;
                        case ASKPLAYCARD:
                            break;
                        case CARDPLAYED:
                            break;
                        default:
                            System.out.println("Error: protocol unknows - command ignored.");
                            break;
                    }
                }
            }
            @Override
            public void disconnected(Connection connection){
                System.out.println("Disconnected from server :( .");
            }
        });

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String s;

        registerPlayer();
        System.out.println("Connecting to Jcoinche server...");
        JcoincheClient.kryonet.connect(5000, Network.ServerIP, Network.ServerPort);

        while ((s = rd.readLine()) != null && s.length() != 0)
        {
            JcoincheClient.kryonet.sendTCP(s);
        }
        JcoincheClient.kryonet.stop();
    }

}
