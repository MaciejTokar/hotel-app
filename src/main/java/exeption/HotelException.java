package exeption;

public class HotelException extends RuntimeException {

    public enum Code {
        DUPLICATE_EXCEPTION("Duplicated value"),
        HOTEL_ID_EXCEPTION("Invalid id of hotel"),
        CLIENT_ID_EXCEPTION("Invalid id of client"),
        HOTEL_NAME_EXCEPTION("Invalid value of hotel name");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }


    public HotelException(Code code) {
        super(code.getFormat());
    }

    public HotelException(Code code, Throwable cause) {
        super(code.getFormat(), cause);
    }
}
