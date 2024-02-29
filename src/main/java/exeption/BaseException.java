package exeption;

public class BaseException extends RuntimeException {
    private final String id;
    private final ErrorCode code;

    public BaseException(ErrorCode code, String id) {
        super("Id: " + id + ", Code: " + code);
        this.id = id;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public ErrorCode getCode() {
        return code;
    }
}
