package exeption;

public class ClientException extends RuntimeException{

    public enum Code {
        DUPLICATE_VALUE("Duplicated value of client"),
        CLIENT_ID_EXCEPTION("Invalid id of client"),
        CLIENT_NULL_EXCEPTION("Value of client is null");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public ClientException(Code code) {
        super(code.getFormat());
    }
}
