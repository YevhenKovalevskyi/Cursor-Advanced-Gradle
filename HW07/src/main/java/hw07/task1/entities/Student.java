package hw07.task1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="s_id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @Column(name="s_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name="f_group_id", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Group group;
}
