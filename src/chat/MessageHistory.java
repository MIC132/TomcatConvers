package chat;

import exporter.Exporter;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import message.MessageBase;

public class MessageHistory {

    final ArrayList<MessageBase> list = new ArrayList<>();
    ServletContext context;

    public MessageHistory(ServletContext context) {
        this.context = context;
    }

    public void add(MessageBase m) {
        list.add(m);
    }

    public synchronized void export(String format) {
        Exporter exporter = Exporter.getExporter(format);
        String path = context.getRealPath("/history." + format);
        exporter.export(list, path);
    }
}
