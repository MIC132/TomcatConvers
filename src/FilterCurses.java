/**
 * Created by vulpes on 22.02.15.
 */
public class FilterCurses extends Filter {
    protected static String work(String message) {
        if (message == null) return null;

        message.replaceAll("kurwa","*****" );
        message.replaceAll("fuck","****" );
        message.replaceAll("pierdol","*******" );
        message.replaceAll("chuj","****" );

        return message;
    }

    public Message handle(Message input){
        if (input == null) return null;
        if(input.message == null) return input;
        if(input.message.isEmpty()) return input;
        input.message = work(input.message);
        return next.handle(input);
    }
}
