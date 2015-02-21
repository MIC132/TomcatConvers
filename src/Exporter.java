import java.util.ArrayList;

public abstract class Exporter {
    static Exporter getExporter(String format){
        if(format.equals("txt")){
            return new TxtExporter();
        }
        return null;
    }
    abstract void export(ArrayList<Message> messages, String path);
}
