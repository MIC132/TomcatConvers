package filters;

/**
 * Created by vulpes on 25.02.15.
 */
public class FilterPL extends Filter {

    protected static String work(String message) {
        String output = message;

        output = output.replaceAll("ą", "a");
        output = output.replaceAll("ć", "c");
        output = output.replaceAll("ę", "e");
        output = output.replaceAll("ł", "l");
        output = output.replaceAll("ó", "o");
        output = output.replaceAll("ń", "n");
        output = output.replaceAll("ś", "s");
        output = output.replaceAll("ź", "z");
        output = output.replaceAll("ż", "z");

        output = output.replaceAll("Ą", "A");
        output = output.replaceAll("Ć", "C");
        output = output.replaceAll("Ę", "E");
        output = output.replaceAll("Ł", "L");
        output = output.replaceAll("Ó", "O");
        output = output.replaceAll("Ń", "N");
        output = output.replaceAll("Ś", "S");
        output = output.replaceAll("Ź", "Z");
        output = output.replaceAll("Ż", "Z");

        return output;
    }

    @Override
    public String handle(String input) {
        String output = work(input);
        return next.handle(output);
    }
}
