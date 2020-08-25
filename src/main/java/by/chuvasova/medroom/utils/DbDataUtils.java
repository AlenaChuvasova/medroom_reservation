package by.chuvasova.medroom.utils;

public class DbDataUtils {

    public String splitFullNames(String fullname) {
        String[] nameData = fullname.split(" ");
        String surname = nameData[1];
        return surname;
    }

    public Integer getRoomIdConverter(String roomNumber){
        Integer roomId = Integer.parseInt(roomNumber);
       return roomId;
    }
}