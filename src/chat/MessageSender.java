package chat;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import message.ColoredMessage;
import message.FilteredMessage;
import message.MessageBase;

public class MessageSender implements Runnable {

    final MessageQueue queue;
    boolean running = true;

    public MessageSender(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (running) {
            ArrayList<MessageBase> pendingMessages;
            synchronized (queue.messages) {
                try {
                    if (queue.messages.isEmpty()) {
                        queue.messages.wait();
                    }
                } catch (InterruptedException e) {
                    // Ignore
                }
                pendingMessages = new ArrayList<>(queue.messages);
                queue.messages.clear();
            }

            synchronized (queue.connections) {
                for (HttpServletResponse connection : queue.connections) {
                    try {
                        PrintWriter writer = connection.getWriter();
                        for (MessageBase pendingMessage : pendingMessages) {
                            MessageBase decorated = new ColoredMessage(new FilteredMessage(pendingMessage));
                            writer.println("<div>" + decorated.toString() + "</div>");
                        }
                        writer.flush();
                    } catch (IOException e) {
                        //TODO: coś tu dać
                    }
                }
            }
        }
    }


}
