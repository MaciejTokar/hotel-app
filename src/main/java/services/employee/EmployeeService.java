package services.employee;

import dao.EmployeeDao;
import dao.HotelDao;
import mapping.EmployeeMapper;
import model.Employee;
import request.EmployeeRequest;
import response.EmployeeResponse;

public class EmployeeService {

    private EmployeeDao employeeDao;
    private HotelDao hotelDao;
    private EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeDao employeeDao, HotelDao hotelDao, EmployeeMapper employeeMapper) {
        this.employeeDao = employeeDao;
        this.hotelDao = hotelDao;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee = upsertEmployee(employee, employeeRequest);

        employeeDao.save(employee);
        return employeeMapper.fromEmployeeToEmployeeResponse(employeeDao.getById(employee.getId()));
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeDao.getById(id);
        employee = upsertEmployee(employee, employeeRequest);

        employeeDao.update(employee);

        return employeeMapper.fromEmployeeToEmployeeResponse(employeeDao.getById(employee.getId()));
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeDao.getById(id);
        employeeDao.delete(employee);
    }

    private Employee upsertEmployee(Employee employee, EmployeeRequest employeeRequest) {
        return employee.builder()
                .id(employee.getId())
                .name(employeeRequest.getName())
                .surname(employeeRequest.getSurname())
                .phone(employeeRequest.getPhone())
                .mail(employeeRequest.getMail())
                .salary(employeeRequest.getSalary())
                .hotel(hotelDao.getById(employeeRequest.getHotelId()))
                .build();
    }
}
