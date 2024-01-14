package exeption;

public class ReviewException extends RuntimeException {

    public enum Code {
        REVIEW_ID_EXCEPTION("Invalid id of review"),
        REVIEW_NULL_EXCEPTION("Value of review is null");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public ReviewException(Code code) {
        super(code.getFormat());
    }
}
