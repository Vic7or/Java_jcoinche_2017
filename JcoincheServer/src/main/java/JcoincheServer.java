import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class JcoincheServer {
    private static class ServerHolder {
        private final static JcoincheServer instance = new JcoincheServer();
    }
    private final static Server kryonet = new Server();
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
                System.out.println("new client connected ! =)");
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
                System.out.println("client has left :( .");
                JcoincheServer.kryonet.stop();
            }
        });
    }
}
