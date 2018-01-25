import java.io.IOException;

public class JcoincheClientMain {
        public static void main(String[] args){
            try {
                JcoincheClient client = new JcoincheClient();
                client.run();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
