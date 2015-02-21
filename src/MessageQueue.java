import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class MessageQueue {
    public final ArrayList<Message> messages = new ArrayList<Message>();
    public final ArrayList<HttpServletResponse> connections;
    protected MessageSender sender;
    protected MessageHistory history;

    public MessageQueue(ArrayList<HttpServletResponse> connections, MessageHistory history) {
        this.connections = connections;
        this.history = history;
        sender = new MessageSender(this);
        Thread messageSenderThread = new Thread(sender);
        messageSenderThread.setDaemon(true);
        messageSenderThread.start();
    }

    public void stop() {
        sender.running = false;
        synchronized (messages) {
            messages.notifyAll();
        }
    }

    public void add(String user, String message) {
        synchronized (messages) {
            Message m = new Message(user,message);
            messages.add(m);
            history.add(m);
            messages.notifyAll();
        }
    }
}