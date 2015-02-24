/**
 * Created by vulpes on 21.02.15.
 */
public class Filter {
    Filter next = null;                                     // następny po nim
    void attach(Filter next_module){
        next = next_module;
    } // doczep następny po nim
    void dettach() { if (next != null) next = next.next;}   // odczep następny po nim
    Filter(Filter next_module){
        next = next_module;
    }      // twórz z od razu doczepionym następnym
    Filter() {next = null; };                               // twórz bez doczepionego
    Message handle(Message input){                          // przetwórz wiadomość
        if(next != null) return next.handle(input);
        return input;
    }
}
