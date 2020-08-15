package by.chuvasova.medroom.service;

import by.chuvasova.medroom.ConnectionFactory;
import by.chuvasova.medroom.dao.impl.EmployeeDaoImpl;
import by.chuvasova.medroom.dao.impl.RoomDaoImpl;
import by.chuvasova.medroom.dto.ReservationDto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {
    // synchronized while fill reservation form
    private static final String GET_EMPLOYEE_FULL_NAME = "SELECT employeeId, CONCAT(name, ' ', surname) as fullname FROM employee where isFree = true;";
    private static final String GET_AVAILABLE_ROOMNUMBER = "SELECT roomNumber FROM room where isAvailable = true;";
    private static final String STOP_RESERVATION = "UPDATE reservation set isActive = false where reservationId = ?";
    public static List<Integer> freeRoom = new ArrayList<>();
    public static Map<Integer, String> fullNames = new HashMap<>();
    ResultSet result = null;
    PreparedStatement preparedStatement = null;
    EmployeeDaoImpl employeeDao;
    RoomDaoImpl roomDao;

    public Map<Integer, String> getEmployeeFullName() throws IOException, ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_EMPLOYEE_FULL_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result = preparedStatement.executeQuery();
        while (result.next()) {
            fullNames.put(
                    result.getInt("employeeId"), result.getString("fullName"));
        }
        return fullNames;
    }

    public List<Integer> getNumberAvailableListRoom() throws IOException, ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_AVAILABLE_ROOMNUMBER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result = preparedStatement.executeQuery();
        while (result.next()) {
            freeRoom.add(result.getInt("roomNumber"));
        }
        return freeRoom;
    }

    public void addReservationFromUI(ReservationDto reservationDto) throws IOException, ClassNotFoundException {
//        Set<Room> rooms = roomDao.getAllAvailableRooms();
//        Set<Employee> employees = employeeDao.getAllAvailableEmployee();


    }

    public void stopReservation(Integer reservationId) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(STOP_RESERVATION);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.setInt(1, reservationId);
        preparedStatement.executeUpdate();
    }
}
