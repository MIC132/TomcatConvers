import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class MessageQueue {
    public final ArrayList<Message> messages = new ArrayList<Message>();
    public final ArrayList<HttpServletResponse> connections;
    protected MessageSender sender = null;

    public MessageQueue(ArrayList<HttpServletResponse> connections) {
        this.connections = connections;
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
            messages.add(new Message(user,message));
            messages.notifyAll();
        }
    }
}