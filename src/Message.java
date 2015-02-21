public class Message {
    public String user;
    public String message;

    public Message(String user, String message) {
        this.message = message;
        this.user = user;
    }

    @Override
    public String toString() {
        return '['+user+"]:"+message;
    }
}
