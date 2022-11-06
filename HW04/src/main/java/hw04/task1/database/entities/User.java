package hw04.task1.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class User extends Entity {
    
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
}
