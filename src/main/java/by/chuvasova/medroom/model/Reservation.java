package by.chuvasova.medroom.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private Integer reservationId;
    private String manipulationName;
    private String description;
    private Date startTime;
    private Date endTime;
    private Boolean isActive;
    private Integer emplId;
    private Integer roomid;

    public Reservation(String manipulationName, String description, Date startTime, Date endTime,
                       Boolean isActive, Integer employeeId, Integer roomId) {
        this.manipulationName = manipulationName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
        this.emplId = employeeId;
        this.roomid = roomId;
    }
}
