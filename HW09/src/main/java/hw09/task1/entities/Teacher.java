package hw09.task1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="teachers")
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="t_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;
    
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name="t_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    @OneToMany(mappedBy="teacher", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Group> groups;
    
    public static Teacher build(Teacher params) {
        return Teacher.builder()
                .name(params.getName())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .build();
    }
    
    public static Teacher build(Integer id, Teacher params) {
        return Teacher.builder()
                .id(id)
                .name(params.getName())
                .createdAt(params.getCreatedAt())
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();
    }
}
