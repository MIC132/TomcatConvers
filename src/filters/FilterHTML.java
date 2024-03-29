package filters;

/**
 * Created by vulpes on 21.02.15.
 */
public class FilterHTML extends Filter {

    protected static String work(String message) {
        if (message == null)
            return null;

        char[] content = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        
        for (char aContent : content) {
            switch (aContent) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(aContent);
            }
        }
        return result.toString();
    }

    @Override
    public String handle(String input) {
        String output = work(input);
        return next.handle(output);
    }
}
