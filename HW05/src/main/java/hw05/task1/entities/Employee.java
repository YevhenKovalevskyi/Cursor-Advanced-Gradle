package hw05.task1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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
    
    @Column(name="e_email", columnDefinition = "VARCHAR(50) NOT NULL")
    private String email;
    
    @Column(name="e_first_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String firstName;
    
    @Column(name="e_last_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String lastName;
    
    @Column(name="e_gender", columnDefinition = "VARCHAR(10) NOT NULL")
    private String gender;
    
    @Column(name="e_age", columnDefinition = "TINYINT UNSIGNED NOT NULL")
    private Integer age;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
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
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
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
