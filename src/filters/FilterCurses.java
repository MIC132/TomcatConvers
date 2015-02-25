package filters;

/**
 * Created by vulpes on 22.02.15.
 */
public class FilterCurses extends Filter {

    protected static String work(String message) {
        String output = message;
        output = output.replaceAll("kurwa", "*****");
        output = output.replaceAll("fuck", "****");
        output = output.replaceAll("pierdol", "*******");
        output = output.replaceAll("chuj", "****");
        return output;
    }

    @Override
    public String handle(String input) {
        String output = work(input);
        return next.handle(output);
    }
}
