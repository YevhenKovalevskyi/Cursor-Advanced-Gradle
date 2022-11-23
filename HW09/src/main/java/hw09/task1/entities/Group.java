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
@Table(name="groupz") // <- this funny table name because of SQL reserved name
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="g_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;
    
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name="g_name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    @OneToMany(mappedBy="group", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Student> students;
    
    @ManyToOne
    @JoinColumn(name="f_teacher_id", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Teacher teacher;
    
    public static Group build(Group params) {
        return Group.builder()
                .name(params.getName())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .teacher(params.getTeacher())
                .build();
    }
    
    public static Group build(Integer id, Group params) {
        return Group.builder()
                .id(id)
                .name(params.getName())
                .createdAt(params.getCreatedAt())
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .teacher(params.getTeacher())
                .build();
    }
}
