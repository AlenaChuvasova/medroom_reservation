package by.chuvasova.medroom.dao;

import by.chuvasova.medroom.model.Reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public interface ReservationDao {
    Set<Reservation> getAllReservation() throws IOException, ClassNotFoundException;

    Reservation addNewReservation(Reservation reservation) throws IOException, ClassNotFoundException;

    Integer getRoomId(int id) throws IOException, ClassNotFoundException;

    Integer getEmployeeId(String fullname) throws IOException, ClassNotFoundException;

    Reservation getReservationById(Integer id) throws IOException, ClassNotFoundException, SQLException;
}
