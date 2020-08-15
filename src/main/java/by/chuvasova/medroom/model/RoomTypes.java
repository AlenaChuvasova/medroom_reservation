package by.chuvasova.medroom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RoomTypes {
    ULTRASOUND("ULTRASOUND"),
    OPHTHALMIC("OPHTHALMIC"),
    DENTAL("DENTAL"),
    PROCEDURAL("PROCEDURAL"),
    OPERATING("OPERATING"),
    CONSULTING("CONSULTING");

    private String name;

    public String getName() {
        return this.name;
    }
}