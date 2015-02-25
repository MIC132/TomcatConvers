/**
 * Created by vulpes on 25.02.15.
 */
public class FilterPL extends Filter {
    protected static String work(String message) {
        if (message == null) return null;

        message.replaceAll("ą","a" );
        message.replaceAll("ć","c" );
        message.replaceAll("ę","e" );
        message.replaceAll("ł","l" );
        message.replaceAll("ó","o" );
        message.replaceAll("ń","n" );
        message.replaceAll("ś","s" );
        message.replaceAll("ź","z" );
        message.replaceAll("ż","z" );

        message.replaceAll("Ą","A" );
        message.replaceAll("Ć","C" );
        message.replaceAll("Ę","E" );
        message.replaceAll("Ł","L" );
        message.replaceAll("Ó","O" );
        message.replaceAll("Ń","N" );
        message.replaceAll("Ś","S" );
        message.replaceAll("Ź","Z" );
        message.replaceAll("Ż","Z" );

        return message;
    }

    Message handle(Message input){
        if (input == null) return null;
        if(input.message == null) return input;
        if(input.message.isEmpty()) return input;
        input.message = work(input.message);
        return next.handle(input);
    }
}