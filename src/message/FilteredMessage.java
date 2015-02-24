package message;

public class FilteredMessage extends MessageDecorator {

    public FilteredMessage(MessageBase message) {
        super(message);
    }

    @Override
    public String toString() {
        return filter(message.toString());
    }

    //Filter for replacint/removing html symbols TODO:replace with CoR
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
