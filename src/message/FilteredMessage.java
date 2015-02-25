package message;

import filters.ChainFilter;
import filters.FilterCurses;
import filters.FilterHTML;
import filters.FilterShorts;

public class FilteredMessage extends MessageDecorator {

    ChainFilter filter;

    public FilteredMessage(MessageBase message) {
        super(message);
        filter = new ChainFilter();
        filter.add(new FilterHTML());
        filter.add(new FilterCurses());
        filter.add(new FilterShorts());
    }

    @Override
    public String toString() {
        return filter.handle(message.toString());
    }

    //filters.Filter for replacint/removing html symbols
    private static String filter(String message) {
        if (message == null) {
            return null;
        }

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

}
