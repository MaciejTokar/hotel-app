package exeption;

public class RoomException extends RuntimeException{

    public enum Code {
        ROOM_DUPLICATE_VALUE("Duplicated value of room"),
        ROOM_ID_EXCEPTION("Invalid id of room"),
        ROOM_NULL_EXCEPTION("Value of room is null");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public RoomException(Code code) {
        super(code.getFormat());
    }
}
