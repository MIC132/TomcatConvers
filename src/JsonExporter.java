import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonExporter extends Exporter {
    @Override
    public void export(ArrayList<Message> messages, String path){
        PrintWriter writer;
        Gson gson = new Gson();
        try {
            writer = new PrintWriter(path);
            String json = gson.toJson(messages);
            writer.print(json);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
