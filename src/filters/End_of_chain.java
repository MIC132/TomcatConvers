package filters;

/**
 * Created by vulpes on 25.02.15.
 */
public class End_of_chain extends Filter {
    //pusty filtr, moze być używany jako placeholder
    @Override
    public String handle(String input){                          // przetwórz wiadomość
        return input;
    }
}
