package exeption;


public class EmployeeException extends BaseException {

    public EmployeeException(ErrorCode code, String id) {
        super(code, id);
    }
}
