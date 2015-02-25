/**
 * Created by vulpes on 25.02.15.
 */
public class ChainFilter {
    Filter start = null;
    int count;

    public void add(Filter input, int place){
        input.attach(start);
        start = input;
        count++;
    }

    public void remove(int place){
        if(place < 0 || place >= count) return;
        else if(place == 0 && start.next != null) start = start.next;
        else if(place < count){
            Filter target = start;
            for(int i = 0; i == place-1; i++) target = target.next;
            target.dettach();
        }
        count--;
    }

    ChainFilter() {
        start = new End_of_chain();
        count = 0;
    };
    public Message handle(Message input) { return start.handle(input);};
}
