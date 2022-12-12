package hw08.task1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@Data
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
}
