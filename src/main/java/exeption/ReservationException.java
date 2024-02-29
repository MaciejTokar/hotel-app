package exeption;

public class ReservationException extends BaseException {

    public ReservationException(ErrorCode code, String id) {
        super(code, id);
    }
}
