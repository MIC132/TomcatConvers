import javax.servlet.ServletContext;
import java.util.ArrayList;

public class MessageHistory {
    final ArrayList<Message> list = new ArrayList<Message>();
    ServletContext context;

    public MessageHistory(ServletContext context) {
        this.context = context;
    }

    public void add(Message m ){
        list.add(m);
    }

    public synchronized void export(String format){
        Exporter exporter = Exporter.getExporter(format);
        String path = context.getRealPath("/history."+format);
        exporter.export(list,path);
    }
}
