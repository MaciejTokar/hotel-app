package exeption;


public class EmployeeException extends RuntimeException{

    public enum Code {
        DUPLICATE_EXCEPTION("Duplicated value of employee"),
        EMPLOYEE_ID_EXCEPTION("Null value of ");

        private final String format;

        Code(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public EmployeeException(Code code) {
        super(code.getFormat());
    }
}
