package filters;

public class FilterAbbrv extends Filter{
    protected static String work(String message) {
        String output = message;

        output = output.replaceAll("\\bAGH\\b","Akademia Górniczo Hutnicza" );
        output = output.replaceAll("\\bUCI\\b","Uczelniane Centrum Informacji" );
        output = output.replaceAll("\\bWD\\b","Wirtualny Dziekanat" );
        output = output.replaceAll("\\bUJ\\b","Uniwersytet Jagielloński" );

        return output;
    }

    @Override
    public String handle(String input){
        String output = work(input);
        return next.handle(output);
    }
}
