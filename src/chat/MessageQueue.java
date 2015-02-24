package chat;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import message.MessageBase;

public class MessageQueue {
    public final ArrayList<MessageBase> messages = new ArrayList<>();
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

    public void add(MessageBase message) {
        synchronized (messages) {
            messages.add(message);
            history.add(message);
            messages.notifyAll();
        }
    }
}