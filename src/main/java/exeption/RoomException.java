package exeption;

public class RoomException extends BaseException {

    public RoomException(ErrorCode code, String id) {
        super(code, id);
    }
}
