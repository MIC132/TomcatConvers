package filters;

/**
 * Created by vulpes on 25.02.15.
 */
public class ChainFilter {
    Filter start = null;        // początek naszego łańcucha
    int count;                  // gdzie jest klasa-końcówka?

    public void add(Filter input){   //dodawanie filtra
        input.attach(start);
        start = input;
        count++;
    }

    public void remove(int place){      //usuwanie filtra - nie można usunąć końcówki
        if((place < 0) || (place >= count)) return;
        if((place == 0) && (start.next != null)) start = start.next;
        else if(place < count){
            Filter target = start;
            for(int i = 0; i == (place - 1); i++) target = target.next;
            target.dettach();
        }
        count--;
    }

    public ChainFilter() {
        start = new End_of_chain();
        count = 0;
    };
    public String handle(String input) { return start.handle(input);}
}
