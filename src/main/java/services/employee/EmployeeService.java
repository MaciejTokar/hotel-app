package services.employee;

import dao.EmployeeDao;
import dao.HotelDao;
import model.Employee;
import request.EmployeeRequest;

public class EmployeeService {

    private EmployeeDao employeeDao;
    private HotelDao hotelDao;

    public EmployeeService(EmployeeDao employeeDao, HotelDao hotelDao) {
        this.employeeDao = employeeDao;
        this.hotelDao = hotelDao;
    }

    public void saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        upsertEmployee(employee, employeeRequest);

        employeeDao.save(employee);
    }

    public void updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeDao.getById(id);
        upsertEmployee(employee, employeeRequest);

        employeeDao.update(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeDao.getById(id);
        employeeDao.delete(employee);
    }

    private void upsertEmployee(Employee employee, EmployeeRequest employeeRequest) {
        employee.setName(employeeRequest.getName());
        employee.setSurname(employeeRequest.getSurname());
        employee.setPhone(employeeRequest.getPhone());
        employee.setMail(employeeRequest.getMail());
        employee.setSalary(employeeRequest.getSalary());
        employee.setHotel(hotelDao.getById(employeeRequest.getHotelId()));
    }
}
