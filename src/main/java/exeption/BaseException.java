package exeption;

public class BaseException extends RuntimeException {
    private String id;
    private String id2;
    private ErrorCode code;

    public BaseException(ErrorCode code, String id) {
        super("Id: " + id + ", Code: " + code);
        this.id = id;
        this.code = code;
    }

    public BaseException(ErrorCode code) {
        super("Code: " + code);
        this.code = code;
    }

    public BaseException(ErrorCode code, String id, String id2) {
        super("Main id: " + id + " Relation id: " + id2 + "Code: " + code);
        this.code = code;
        this.id = id;
        this.id2 = id2;
    }

    public String getId() {
        return id;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getId2() {
        return id2;
    }
}
