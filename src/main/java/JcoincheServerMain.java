import java.io.IOException;

public class JcoincheServerMain {
    public static void main(String[] args){
        try {
            JcoincheServer server = new JcoincheServer();
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
