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

@Table(name = "employee")
public class Employee implements Comparable<Employee> {
    private Integer employeeId;
    private String name;
    private String surname;
    private String position;
    private Boolean isFree;

    public Employee(String name, String surname, String position ) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.isFree = true;
    }

    @Override
    public int compareTo(Employee e) {
        return employeeId - e.employeeId;
    }
}
