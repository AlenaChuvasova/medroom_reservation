package by.chuvasova.medroom.service;

import by.chuvasova.medroom.ConnectionFactory;
import by.chuvasova.medroom.dao.ReservationDao;
import by.chuvasova.medroom.dao.impl.ReservationDaoImpl;
import by.chuvasova.medroom.dto.ReservationDto;
import by.chuvasova.medroom.model.Reservation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReservationService {
    private static final String GET_EMPLOYEE_FULL_NAME = "SELECT employeeId, CONCAT(name, ' ', surname) as fullname FROM employee where isFree = true";
    private static final String GET_AVAILABLE_ROOMNUMBER = "SELECT roomNumber FROM room where isAvailable = true";
    private static final String STOP_RESERVATION = "UPDATE reservation set isActive = false where reservationId = ?";
    private static final String GET_RESERVE_DATA = "SELECT reservationId, CONCAT(name, ' ', surname) as fullname, " +
            "manipulationName, description, startTime, " +
            "endTime, isActive, roomNumber, emplId, rsrv.roomid FROM reservation as rsrv" +
            "INNER JOIN room as r ON rsrv.roomid = r.roomId" +
            "INNER JOIN employee as empl ON rsrv.emplId = empl.employeeId";
    private static final String UPDATE_EMPLOYEE_STATUS = "update employee as e join reservation as rsrv on rsrv.emplId = e.employeeId set isFree = false";
    private static final String UPDATE_ROOM_STATUS = "update room as r join reservation as rsrv on rsrv.roomid = r.roomId set isAvailable = false";
    //private static final String UPDATE_RESERVATION_STATUS = "...";
    public static List<Integer> freeRoom = new ArrayList<>();
    public static Map<Integer, String> fullNames = new HashMap<>();
    public static List<ReservationDto> reservs = new ArrayList<>();

    ResultSet result = null;
    PreparedStatement preparedStatement = null;
    ReservationDao reservationDao = new ReservationDaoImpl();

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

    public List<ReservationDto> getListOfReserves() throws IOException, ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        preparedStatement = connection.prepareStatement(GET_RESERVE_DATA);
        result = preparedStatement.executeQuery();
        while (result.next()) {
            reservs.add(new ReservationDto(
                    result.getInt("dtoId"),
                    result.getString("fullName"),
                    result.getString("manipulationName"),
                    result.getString("description"),
                    result.getDate("startTime"),
                    result.getDate("endTime"),
                    result.getBoolean("isActive"),
                    result.getInt("roomNumber")));
        }
        return reservs;
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

    public synchronized void addReservationFromUI(String manipulationName, String description, Date startTime, Date endTime, Boolean status, Integer employeeId, Integer roomId) throws IOException, ClassNotFoundException {
        reservationDao.addNewReservation(new Reservation(manipulationName, description, startTime, endTime, status, employeeId, roomId));
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

    public void updateRoomStatus() throws IOException, ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ROOM_STATUS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.executeUpdate();

    }

    public void updateEmployeeStatus() throws IOException, ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_STATUS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.executeUpdate();
    }

    public void updateReservationStatus() throws IOException, ClassNotFoundException, SQLException {
//        Connection connection = ConnectionFactory.getConnection();
//        try {
//            preparedStatement = connection.prepareStatement(UPDATE_RESERVATION_STATUS);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        preparedStatement.executeUpdate();

    }
}
