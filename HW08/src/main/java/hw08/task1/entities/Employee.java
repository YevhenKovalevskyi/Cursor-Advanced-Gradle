package hw08.task1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="e_id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @NotEmpty(message = "Name cannot be null or empty!")
    @Email(message = "Email is invalid!")
    @Column(name="e_email", columnDefinition = "VARCHAR(50) NOT NULL")
    private String email;
    
    @NotEmpty(message = "Firstname cannot be null or empty!")
    @Column(name="e_first_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String firstName;
    
    @NotEmpty(message = "Lastname cannot be null or empty!")
    @Column(name="e_last_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String lastName;
    
    @NotEmpty(message = "Gender cannot be null or empty!")
    @Pattern(message = "Gender is invalid!", regexp = "male|female")
    @Column(name="e_gender", columnDefinition = "VARCHAR(10) NOT NULL")
    private String gender;
    
    @NotEmpty(message = "Age cannot be null or empty!")
    @Positive(message = "Age is invalid!")
    @Min(message = "Minimal age is 18!", value = 18)
    @Column(name="e_age", columnDefinition = "TINYINT UNSIGNED NOT NULL")
    private Integer age;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name="f_shop_id", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Shop shop;
    
    public static Employee build(Employee params) {
        return Employee.builder()
                .email(params.getEmail())
                .firstName(params.getFirstName())
                .lastName(params.getLastName())
                .gender(params.getGender())
                .age(params.getAge())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .shop(params.getShop())
                .build();
    }
    
    public static Employee build(Integer id, Employee params) {
        return Employee.builder()
                .id(id)
                .email(params.getEmail())
                .firstName(params.getFirstName())
                .lastName(params.getLastName())
                .gender(params.getGender())
                .age(params.getAge())
                .createdAt(params.getCreatedAt())
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .shop(params.getShop())
                .build();
    }
}
