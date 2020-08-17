package by.chuvasova.medroom.dao.impl;

import by.chuvasova.medroom.ConnectionFactory;
import by.chuvasova.medroom.dao.ReservationDao;
import by.chuvasova.medroom.model.Reservation;
import by.chuvasova.medroom.utils.DbUtils;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ReservationDaoImpl implements ReservationDao {
    private static final String GET_ALL_RESERVATIONS = "SELECT * FROM reservation";
    private static final String ADD_NEW_RESERVATION = "INSERT INTO reservation(manipulationName, description, startTime, endTime, " +
            "isActive, emplId, roomid) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_RESERVATION_BY_ID = "SELECT * FROM reservation where reservationId = ?";
    private static Set<Reservation> reservations = new HashSet<>();
    Reservation reservation;
    ResultSet result = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Set<Reservation> getAllReservation() throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_RESERVATIONS);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                reservation = new Reservation();
                reservation.setManipulationName(result.getString("manipulationName"));
                reservation.setDescription(result.getString("description"));
                reservation.setStartTime(result.getDate("startTime"));
                reservation.setEndTime(result.getDate("endTime"));
                reservation.setIsActive(result.getBoolean("isActive"));
                reservation.setEmplId(result.getInt("emplId"));
                reservation.setRoomid(result.getInt("roomid"));
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return reservations;
    }

    @Override
    public synchronized Reservation addNewReservation(Reservation reservation) throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(ADD_NEW_RESERVATION);
            preparedStatement.setString(1, reservation.getManipulationName());
            preparedStatement.setString(2, reservation.getDescription());
            preparedStatement.setTimestamp(3, new Timestamp(reservation.getStartTime().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(reservation.getEndTime().getTime()));
            preparedStatement.setBoolean(5, reservation.getIsActive());
            preparedStatement.setInt(6, reservation.getEmplId());
            preparedStatement.setInt(7, reservation.getRoomid());
            preparedStatement.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return reservation;
    }

    @Override
    public Reservation getReservationById(Integer id) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_RESERVATION_BY_ID);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            if (result.next()) {
                Integer reservationId = result.getInt("reservationId");
                String manipulationName = result.getString("manipulationName");
                String description = result.getString("description");
                Date startTime = result.getDate("startTime");
                Date endTime = result.getDate("endTime");
                Boolean isActive = result.getBoolean("isActive");
                Integer employeeId = result.getInt("emplId");
                Integer roomid = result.getInt("roomid");
                reservation = new Reservation(reservationId, manipulationName, description, startTime,
                        endTime, isActive, employeeId, roomid
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return reservation;
    }
}

