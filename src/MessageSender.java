import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MessageSender implements Runnable {
    protected boolean running = true;
    protected final ArrayList<String> messages = new ArrayList<String>();
    protected final ArrayList<HttpServletResponse> connections;

    public MessageSender(ArrayList<HttpServletResponse> connections) {
        this.connections = connections;
    }

    public void stop() {
        running = false;
        synchronized (messages) {
            messages.notify();
        }
    }

    public void send(String user, String message) {
        synchronized (messages) {
            messages.add('[' + user + "]: " + message);
            messages.notify();
        }
    }

    @Override
    public void run() {
        while (running) {
            String[] pendingMessages;
            synchronized (messages) {
                try {
                    if (messages.isEmpty()) {
                        messages.wait();
                    }
                } catch (InterruptedException e) {
                    // Ignore
                }
                pendingMessages = messages.toArray(new String[messages.size()]);
                messages.clear();
            }

            synchronized (connections) {
                for (HttpServletResponse connection : connections) {
                    try {
                        PrintWriter writer = connection.getWriter();
                        for (String pendingMessage : pendingMessages) {
                            writer.println("<div>" + filter(pendingMessage) + "</div>");
                        }
                        writer.flush();
                    } catch (IOException e) {
                        //TODO: coś tu dać
                    }
                }
            }
        }
    }

    //Filter for replacint/removing html symbols TODO:replace with CoR
    protected static String filter(String message) {
        if (message == null) return null;

        char[] content = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (char aContent : content) {
            switch (aContent) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(aContent);
            }
        }
        return result.toString();
    }
}