package message;

public class ColoredMessage extends MessageDecorator {

    private final String color;

    public ColoredMessage(MessageBase message) {
        super(message);
        // We can't directly use message.user, because user belongs to Message and not to MessageBase
        String user = message.toString().substring(message.toString().indexOf("[") + 1, message.toString().indexOf("]"));
        switch (user) {
            case "Tomcat":
                this.color = "black";
                break;
            default:
                this.color = strToColorHex(user);
        }
    }

    @Override
    public String toString() {
        return "<p style=\"color:" + color + ";\">" + message.toString() + "</p>";
    }

    private static String strToColorHex(String str) {
        int hex = str.hashCode() << 8 >>> 8;
        return String.format("#%06x", hex);
    }
}
