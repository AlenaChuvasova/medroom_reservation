package by.chuvasova.medroom.dao.impl;

import by.chuvasova.medroom.ConnectionFactory;
import by.chuvasova.medroom.dao.RoomDao;
import by.chuvasova.medroom.model.Room;
import by.chuvasova.medroom.utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

public class RoomDaoImpl implements RoomDao {
    //lock dao while create
    private static final String GET_AVAILABLE_ROOM = "SELECT * FROM room where isAvailable=true";
    private static final String GET_ROOM_BY_NUMBER = "SELECT * FROM room where roomNumber=?";
    private static final String ADD_NEW_ROOM = "INSERT INTO room(roomNumber, roomType, isAvailable) VALUES(?, ?, true)";
    private static Set<Room> rooms = new TreeSet<>();
    Room room;
    ResultSet result = null;
    PreparedStatement preparedStatement = null;

    @Override
    public Set<Room> getAllAvailableRooms() throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_AVAILABLE_ROOM);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                room = new Room();
                room.setRoomId(result.getInt("roomId"));
                room.setRoomNumber(result.getInt("roomNumber"));
                room.setRoomType(result.getString("roomType"));
                room.setIsAvailable(result.getBoolean("isAvailable"));
                rooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return rooms;
    }

    @Override
    public Room getRoomByNumber(Integer number) throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_ROOM_BY_NUMBER);
            preparedStatement.setInt(1, number);
            result = preparedStatement.executeQuery();
            if (result.next()) {
                Integer roomId = result.getInt("roomId");
                Integer roomNumber = result.getInt("roomNumber");
                String roomType = result.getString("roomType");
                Boolean isAvailable = result.getBoolean("isAvailable");
                room = new Room(roomId, roomNumber, roomType, isAvailable);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return room;
    }

    @Override
    public Room addNewRoom(Room room) throws IOException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(ADD_NEW_ROOM);
            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setString(2, room.getRoomType());
            preparedStatement.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection);
            DbUtils.close(preparedStatement);
            DbUtils.close(result);
        }
        return room;
    }
}
