package exeption;

public class ReservationException extends RuntimeException {

    public enum Code {
        RESERVATION_DUPLICATE_VALUE("Duplicated value of reservation"),
        RESERVATION_ID_EXCEPTION("Invalid id of reservation"),
        RESERVATION_NULL_EXCEPTION("Value of reservation is reservation");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public ReservationException(Code code) {
        super(code.getFormat());
    }

}
