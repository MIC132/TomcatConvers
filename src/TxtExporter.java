import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TxtExporter extends Exporter {
    @Override
    public void export(ArrayList<Message> messages, String path){
        PrintWriter writer;
        try {
            writer = new PrintWriter(path);
            for(Message m : messages){
                writer.println(m.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
