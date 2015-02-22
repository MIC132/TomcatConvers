/**
 * Created by vulpes on 22.02.15.
 */
public class FilterCurses extends Filter {
    protected static String work(String message) {
        if (message == null) return null;

        message.replaceAll("kurwa","*****" );
        message.replaceAll("fuck","****" );

        return message;
    }

    Message handle(Message input){
        input.message = work(input.message);
        if(next != null) return next.handle(input);
        return input;
    }
}
