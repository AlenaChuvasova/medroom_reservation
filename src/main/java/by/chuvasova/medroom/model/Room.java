package by.chuvasova.medroom.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "room")
public class Room implements Comparable<Room>{
    private Integer roomId;
    private Integer roomNumber;
    private String roomType;
    private Boolean isAvailable;

    public Room(Integer roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
    }

    @Override
    public int compareTo(Room room) {
        return roomNumber - room.roomNumber;
    }
}
