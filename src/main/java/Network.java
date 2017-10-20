import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    public final static int       ServerPort = 4242;
    public final static String    ServerIP = "192.168.1.25";
    public static void register(EndPoint endp) {
        Kryo kryo = endp.getKryo();
        kryo.register(String.class);
    }
}
