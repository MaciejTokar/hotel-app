package exeption;

public class GeneralException extends RuntimeException{

    public enum Code {
        TRANSACTION_EXCEPTION("Transaction not executed."),
        SESSION_EXCEPTION("Database operation error"),
        INITIALIZER_EXCEPTION("Initial SessionFactory creation failed.");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public GeneralException(Code code) {
        super(code.getFormat());
    }
}
