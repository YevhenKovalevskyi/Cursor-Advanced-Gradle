package hw10.task1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="groupz") // <- this funny table name because of SQL reserved name
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="g_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;
    
    @Column(name="g_name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String updatedAt;
    
    @OneToMany(mappedBy="group", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Student> students;
    
    @ManyToOne
    @JoinColumn(name="f_teacher_id", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Teacher teacher;
}
