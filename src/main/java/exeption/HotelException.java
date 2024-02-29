package exeption;

public class HotelException extends BaseException {
    public HotelException(ErrorCode code, String id) {
        super(code, id);
    }
}
