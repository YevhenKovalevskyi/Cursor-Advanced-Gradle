package hw09.task1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
@Table(name="students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="s_id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name="s_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name="f_group_id", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Group group;
    
    public static Student build(Student params) {
        return Student.builder()
                .name(params.getName())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .group(params.getGroup())
                .build();
    }
    
    public static Student build(Integer id, Student params) {
        return Student.builder()
                .id(id)
                .name(params.getName())
                .createdAt(params.getCreatedAt())
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .group(params.getGroup())
                .build();
    }
}
