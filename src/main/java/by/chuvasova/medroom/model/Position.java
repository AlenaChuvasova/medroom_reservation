package by.chuvasova.medroom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Position {
    DOCTOR("DOCTOR"),
    NURSE("NURSE"),
    ASSISTANT("ASSISTANT"),
    CLEANER("CLEANER");

    private String name;

    public String getName() {
        return this.name;
    }
}
