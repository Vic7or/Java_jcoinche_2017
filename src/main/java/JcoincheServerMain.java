import java.io.*;


public class JcoincheServerMain {
    public static void main(String[] args){
        try {
            JcoincheServer JCserver = JcoincheServer.getInstance();
            JCserver.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
