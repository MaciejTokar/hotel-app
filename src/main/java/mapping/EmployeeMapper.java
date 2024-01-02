package mapping;

import model.Employee;
import response.EmployeeResponse;

public class EmployeeMapper {

    public EmployeeResponse bySurname(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .salary(employee.getSalary())
                .hotelId(employee.getHotel().getId())
                .build();
    }
}
