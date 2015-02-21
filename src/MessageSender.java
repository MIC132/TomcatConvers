import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MessageSender implements Runnable {
    final MessageQueue queue;
    boolean running = true;

    public MessageSender(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (running) {
            ArrayList<Message> pendingMessages;
            synchronized (queue.messages) {
                try {
                    if (queue.messages.isEmpty()) {
                        queue.messages.wait();
                    }
                } catch (InterruptedException e) {
                    // Ignore
                }
                pendingMessages = new ArrayList<Message>(queue.messages);
                queue.messages.clear();
            }

            synchronized (queue.connections) {
                for (HttpServletResponse connection : queue.connections) {
                    try {
                        PrintWriter writer = connection.getWriter();
                        for (Message pendingMessage : pendingMessages) {
                            writer.println("<div>" + filter(pendingMessage.toString()) + "</div>");
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
