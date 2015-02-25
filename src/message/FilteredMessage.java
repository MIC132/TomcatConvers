package message;

import filters.*;

public class FilteredMessage extends MessageDecorator {

    ChainFilter filter;

    public FilteredMessage(MessageBase message) {
        super(message);
        filter = new ChainFilter();
        filter.add(new FilterHTML());
        filter.add(new FilterCurses());
        filter.add(new FilterAbbrv());
    }

    @Override
    public String toString() {
        return filter.handle(message.toString());
    }
}
