import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.*;

public class JcoincheClientMain {
    public static void main(String[] args){
        try {
            JcoincheClient JCclient = JcoincheClient.getInstance();
            JCclient.run();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
