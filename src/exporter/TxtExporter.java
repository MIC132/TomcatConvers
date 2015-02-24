package exporter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import message.MessageBase;

public class TxtExporter extends Exporter {

    @Override
    public void export(ArrayList<MessageBase> messages, String path) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(path);
            for (MessageBase m : messages) {
                writer.println(m.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
        }
    }
}
