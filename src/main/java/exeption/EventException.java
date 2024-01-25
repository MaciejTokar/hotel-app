package exeption;

public class EventException extends RuntimeException {

    public enum Code {
        EVENT_ID_EXCEPTION("Invalid id value of event"),
        EVENT_NULL_EXCEPTION("Value of event is null");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public EventException(Code code) {
        super(code.getFormat());
    }
}
