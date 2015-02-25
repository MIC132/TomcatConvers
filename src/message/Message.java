package message;

public class Message implements MessageBase {

    public final String user;
    public final String message;

    public Message(String user, String message) {
        this.message = message;
        this.user = user;
    }

    @Override
    public String toString() {
        return '[' + user + "] " + message;
    }
}
