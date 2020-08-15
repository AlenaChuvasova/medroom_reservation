package by.chuvasova.medroom.dao;

import by.chuvasova.medroom.model.Room;

import java.io.IOException;
import java.util.Set;

public interface RoomDao {
    Set<Room> getAllAvailableRooms() throws IOException, ClassNotFoundException;

    Room getRoomByNumber(Integer roomNumber) throws IOException, ClassNotFoundException;

    Room addNewRoom(Room room) throws IOException, ClassNotFoundException;
}
