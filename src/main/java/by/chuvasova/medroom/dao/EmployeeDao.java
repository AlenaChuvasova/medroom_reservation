package by.chuvasova.medroom.dao;

import by.chuvasova.medroom.model.Employee;

import java.io.IOException;
import java.util.Set;

public interface EmployeeDao {
    Set<Employee> getAllAvailableEmployee() throws IOException, ClassNotFoundException;

    Employee addNewEmployee(Employee employee) throws IOException, ClassNotFoundException;

    Employee getEmployeeById(Integer id) throws IOException, ClassNotFoundException;
}
