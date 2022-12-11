package hw06.task1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="countries")
public class Country {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_id", columnDefinition = "TINYINT UNSIGNED")
    private Integer id;
    
    @Column(name="c_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String updatedAt;
    
    @OneToMany(mappedBy="country", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Product> products;
}
