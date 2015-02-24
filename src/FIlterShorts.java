/**
 * Created by vulpes on 24.02.15.
 */
public class FIlterShorts extends Filter {
    protected static String work(String message) {
        if (message == null) return null;

        message.replaceAll(" AGH "," Akademia Górniczo Hutnicza " );
        message.replaceAll(" UCI "," Uczelniane Centrum Informacji " );
        message.replaceAll(" WD "," Wirtualny Dziekanat " );
        message.replaceAll(" UJ "," Uniwersytet Jagielloński " );

        return message;
    }

    Message handle(Message input){
        if (input == null) return null;
        if(input.message == null) return input;
        if(input.message.isEmpty()) return input;
        input.message = work(input.message);
        if(next != null) return next.handle(input);
        return input;
    }
}