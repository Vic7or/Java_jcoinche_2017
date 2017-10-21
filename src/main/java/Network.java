import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    public final static int       ServerPort = 4243;
    public final static String    ServerIP = "192.168.1.25";
    public enum Protocol {
        WAITFORPLAYERS("Waiting for players: ", null){
            @Override
            public void print() {

            }
        },
        REJECTCLIENT("Your connection has been rejected - Jcoinche server is full.", null) {
            @Override
            public void print() {

            }
        },
        LEAVER("Someone has left !", null) {
            @Override
            public void print() {

            }
        },
        READY("Everyone is ready, the game starts!", null) {
            @Override
            public void print() {

            }
        },
        NEWTURN("\n\n\n------ Your turn begins! ------", null) {
            @Override
            public void print() {

            }
        },
        ENDTURN("------ Your turn ends ------", null) {
            @Override
            public void print() {

            }
        },
        ASKGAMBLE("Do you want to raise or fold for this gamble: ", null) {
            @Override
            public void print() {

            }
        },
        RAISEGAMBLE("You raised the gamble to: ", null) {
            @Override
            public void print() {

            }
        },
        FOLDGAMBLE("You folded.", null) {
            @Override
            public void print() {

            }
        },
        ASKPLAYCARD("Which card do you want to play ?", null) {
            @Override
            public void print() {

            }
        },
        PLAYCARD("You played the card: ", null){
            @Override
            public void print(){

            }
        },
        CARDPLAYED(" played the card: ", null){
            @Override
            public void print() {

            }
        },
        WHOISPLAYING(" is playing...", null){
            @Override
            public void print() {

            }
        };

        private final String    what;
        private Object          data;

        Protocol(String id, Object object){
            this.what = id;
            this.data = object;
        }
        public void setData(Object object) {
            this.data = object;
        }
        public Object getData() {
            return this.data;
        }
        public String getWhat() {
            return this.what;
        }
        public abstract void print();
    }
    public static void register(EndPoint endp) {
        Kryo kryo = endp.getKryo();
        kryo.register(String.class);
        kryo.register(Protocol.class);
    }
}
