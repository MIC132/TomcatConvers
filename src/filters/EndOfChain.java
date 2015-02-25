package filters;

/**
 * Created by vulpes on 25.02.15. 
 * pusty filtr, moze być używany jako placeholder
 */
public class EndOfChain extends Filter {

    @Override
    public String handle(String input) { // przetwórz wiadomość
        return input;
    }
}
