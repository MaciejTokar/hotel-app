package dao;

import exeption.EmployeeException;
import exeption.ErrorCode;
import model.Employee;

import java.util.Optional;

public class EmployeeDao extends CommonDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }

    @Override
    public Employee getById(Long id) {
        return Optional.ofNullable(id)
                .filter(_id -> _id != null)
                .map(e -> executeInSession(session -> session.get(Employee.class, id)))
                .orElseThrow(() -> new EmployeeException(ErrorCode.EMPLOYEE_ID_EXCEPTION, String.valueOf(id)));
    }
}
