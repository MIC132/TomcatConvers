package filters;

/**
 * Created by vulpes on 21.02.15.
 */
public abstract class Filter {

    Filter next = null; // następny po nim

    final void attach(Filter next_module) {    // doczep następny po nim
        next = next_module;
    }

    final void detach() {                      // odczep następny po nim
        if (next!=null)
            next = next.next;
    }  

    Filter(Filter next_module) {         // twórz z od razu doczepionym następnym
        attach(next_module);
    }

    Filter() {                           // twórz bez doczepionego
        next = null;
    }

    public String handle(String input) { // przetwórz wiadomość
        return next.handle(input);
    }
}
