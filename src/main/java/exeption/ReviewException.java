package exeption;

public class ReviewException extends BaseException {

    public ReviewException(ErrorCode code, String id) {
        super(code, id);
    }
}
