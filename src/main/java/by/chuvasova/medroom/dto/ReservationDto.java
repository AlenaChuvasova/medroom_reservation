package by.chuvasova.medroom.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class ReservationDto {
    private Integer id;
    private String manipulationName;
    private String description;
    private Date startTime;
    private Date endTime;
    private Boolean isActive;
    private String fullName;
    private Integer roomNumber;
}
