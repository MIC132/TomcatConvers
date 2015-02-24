package message;

public abstract class MessageDecorator implements MessageBase {

    protected MessageBase message;

    public MessageDecorator(MessageBase message) {
        this.message = message;
    }

    @Override
    public abstract String toString();
}
