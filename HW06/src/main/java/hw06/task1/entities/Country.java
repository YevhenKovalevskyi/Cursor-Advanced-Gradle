package hw06.task1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
@Table(name="countries")
public class Country {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_id", columnDefinition = "TINYINT UNSIGNED")
    private Integer id;
    
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name="c_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    @OneToMany(mappedBy="country", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Product> products;
    
    public static Country build(Country params) {
        return Country.builder()
                .name(params.getName())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .build();
    }
    
    public static Country build(Integer id, Country params) {
        return Country.builder()
                .id(id)
                .name(params.getName())
                .createdAt(params.getCreatedAt())
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();
    }
}
