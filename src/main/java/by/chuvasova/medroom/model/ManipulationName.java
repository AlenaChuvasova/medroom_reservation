package by.chuvasova.medroom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ManipulationName {
    EXAMINATION("EXAMINATION"),
    OPERATION("OPERATION"),
    TESTS("TESTS"),
    CONSULTATION("CONSULTATION");

    private String name;

    public String getName() {
        return this.name;
    }
}