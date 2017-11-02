public class Packet {
    private Network.Protocol type;
    private Object      data;
    Packet(Network.Protocol protocol, Object data){
        this.type = protocol;
        this.data = data;
    }
    Packet(){
        this.type = null;
        this.data = null;
    }
    public Object getData() {
        return this.data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Network.Protocol getType() {
        return this.type;
    }
    public String getTypeWhat() {
        return this.type.getWhat();
    }
}

