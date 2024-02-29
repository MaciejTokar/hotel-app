package exeption;

public class EventException extends BaseException {

    public EventException(ErrorCode code, String id) {
        super(code, id);
    }
}
