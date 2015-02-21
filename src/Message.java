public class Message {
    public String user;
    public String message;

    public Message(String message, String user) {
        this.message = message;
        this.user = user;
    }

    @Override
    public String toString() {
        return '['+user+"]:"+message;
    }
}
