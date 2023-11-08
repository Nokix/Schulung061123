package db.schulung.Schulung061123.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Accessors(chain = true)
public class Student {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    Long id;

    @NotBlank
    String name;

    public Student(String name) {
        this.name = name;
    }
}
