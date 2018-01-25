import com.esotericsoftware.kryonet.Connection;

import java.util.ArrayList;

public class JClient extends Connection{
    public String           name;
    public Hand             hand;
    public ArrayList<Card>  stock;
    public int              points;
}
