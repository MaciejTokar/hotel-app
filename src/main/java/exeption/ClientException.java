package exeption;

public class ClientException extends BaseException {

    public ClientException(ErrorCode code, String id) {
        super(code, id);
    }

}
