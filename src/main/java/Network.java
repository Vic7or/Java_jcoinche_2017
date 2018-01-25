import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    public final static int       ServerPort = 4243;
    public final static String    ServerIP = "192.168.1.25";
    public enum Protocol {
        ASKNAME("ask_name"), //from server to client
        NAME("player_name"), // from client to server
        WAITFORPLAYERS("Waiting for players: "), //from server to all clients
        REJECTCLIENT("Your connection has been rejected - Jcoinche server is full."), //from server to client
        LEAVER("Leaver: "), //from server to all clients
        READY("Everyone is ready, the game starts!"), //from server to all clients
        NEWTURN("\n\n\n------ Your turn begins! ------"), //from server to client
        ENDTURN("------ Your turn ends ------"), //from server to client
        ASKGAMBLE("Do you want to raise or fold for this gamble: "), //from server to client
        RAISEGAMBLE("You raised the gamble to: "), //from client to server
        FOLDGAMBLE("You folded."), //from client to server
        ASKPLAYCARD("Which card do you want to play ?"), //from server to client
        PLAYCARD("You played the card: "),// from client to server
        CARDPLAYED(" played the card: "), // from server to all clients except player
        WHOISPLAYING(" is playing..."),// from server to all clients except player
        ENDGAME("The game is over, the winner is: "); // from server to all clients

        private final String    what;

        Protocol(String id){
            this.what = id;
        }
        public String getWhat() {
            return this.what;
        }
    }
    public static void register(EndPoint endp) {
        Kryo kryo = endp.getKryo();
        kryo.register(String.class);
        kryo.register(Protocol.class);
        kryo.register(Packet.class);
    }

}
