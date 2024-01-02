package services.employee;


import dao.EmployeeDao;
import mapping.EmployeeMapper;
import response.EmployeeResponse;

import java.util.List;

public class EmployeeReportService {

    private EmployeeDao employeeDao;
    private EmployeeMapper employeeMapper;

    public EmployeeReportService(EmployeeDao employeeDao, EmployeeMapper employeeMapping) {
        this.employeeDao = employeeDao;
        this.employeeMapper = employeeMapping;
    }

    public List<EmployeeResponse> searchEmployee(String surname) {
        return employeeDao.findAll().stream()
                .filter(o -> o.getSurname().equalsIgnoreCase(surname))
                .map(employeeMapper::bySurname)
                .toList();
    }
}
