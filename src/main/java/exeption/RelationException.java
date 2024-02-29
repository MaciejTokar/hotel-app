package exeption;

public class RelationException extends RuntimeException {
    private final String id;
    private final ErrorCode code;
    private final String id2;

    public RelationException(ErrorCode code, String id, String id2) {
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
