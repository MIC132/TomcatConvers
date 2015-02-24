/**
 * Created by vulpes on 21.02.15.
 */
public class Filter {
    Filter next = null;
    void attach(Filter next_module){
        next = next_module;
    }
    Filter(Filter next_module){
        next = next_module;
    }
    Filter() {next = null; };
    Message handle(Message input){
        return input;
    }
}
