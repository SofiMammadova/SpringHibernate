package az.perfect.inter;

import az.perfect.model.Employee;

import java.util.List;

public interface EmployeeDaoInter {
    void insert(Employee employee);
    void update(Employee employee);
    void delete(int id);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
}
