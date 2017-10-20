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
    final static Client kryonet = new Client();
    private String      buffer;
    public static JcoincheClient getInstance() {
        return ClientHolder.instance;
    }
    private JcoincheClient() {
        System.out.println("JcoincheClient instantiated.");
    }
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
            }
            @Override
            public void disconnected(Connection connection){
                System.out.println("Disconnected from server :( .");
            }
        });
        JcoincheClient.kryonet.connect(5000, Network.ServerIP, Network.ServerPort);
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = rd.readLine()) != null && s.length() != 0)
        {
            JcoincheClient.kryonet.sendTCP(s);
        }
        JcoincheClient.kryonet.stop();
    }

}
