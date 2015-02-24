package exporter;

import java.util.ArrayList;
import message.MessageBase;

public abstract class Exporter {

    public static Exporter getExporter(String format) {
        switch (format) {
            case "txt":
                return new TxtExporter();
            case "json":
                return new JsonExporter();
            default:
                return null; // better raise exception here man
        }
    }

    public abstract void export(ArrayList<MessageBase> messages, String path);
}
