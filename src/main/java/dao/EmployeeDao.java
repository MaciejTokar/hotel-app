package dao;

import model.Employee;

public class EmployeeDao extends CommonDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }
}
