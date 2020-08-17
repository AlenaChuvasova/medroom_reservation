package by.chuvasova.medroom.dao.impl;

import by.chuvasova.medroom.ConnectionFactory;
import by.chuvasova.medroom.dao.EmployeeDao;
import by.chuvasova.medroom.model.Employee;
import by.chuvasova.medroom.utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final String GET_AVAILABLE_EMPLOYEE = "SELECT * FROM employee where isFree = true";
    private static final String ADD_NEW_EMPLOYEE = "INSERT INTO employee(name, surname, position, isFree) VALUES(?, ?, ?, true)";
    private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employee where employeeId = ?";
    private static Set<Employee> employees = new HashSet<>();
    Employee employee;
    ResultSet result = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Set<Employee> getAllAvailableEmployee() throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_AVAILABLE_EMPLOYEE);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                employee = new Employee();
                employee.setEmployeeId(result.getInt("employeeId"));
                employee.setName(result.getString("name"));
                employee.setSurname(result.getString("surname"));
                employee.setPosition(result.getString("position"));
                employee.setIsFree(result.getBoolean("isFree"));
                employees.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return employees;
    }

    @Override
    public synchronized Employee addNewEmployee(Employee employee) throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(ADD_NEW_EMPLOYEE);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return employee;
    }

    @Override
    public Employee getEmployeeById(Integer id) throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            if (result.next()) {
                Integer employeeId = result.getInt("employeeId");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String position = result.getString("position");
                Boolean isFree = result.getBoolean("isFree");
                employee = new Employee(employeeId, name, surname, position, isFree);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return employee;
    }
}
