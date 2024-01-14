package services.employee;


import dao.EmployeeDao;
import mapping.EmployeeMapper;
import response.EmployeeResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                .map(employeeMapper::fromEmployeeToEmployeeResponse)
                .toList();
    }

    public Map<String, BigDecimal> avgSalary() {
        Map<String, Double> avgValues = employeeDao.findAll().stream()
                .collect(Collectors.groupingBy(e -> e.getHotel().getName(), Collectors.averagingDouble(o -> o.getSalary().doubleValue())));

        return avgValues.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> BigDecimal.valueOf(entry.getValue())));
    }
}
