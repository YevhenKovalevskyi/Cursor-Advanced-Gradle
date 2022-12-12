package hw08.task1.entities;

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
@Table(name="shops")
public class Shop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="s_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;
    
    @Column(name="s_name", columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;
    
    @Column(name="s_city", columnDefinition = "VARCHAR(50) NOT NULL")
    private String city;
    
    @Column(name="s_address", columnDefinition = "VARCHAR(100) NOT NULL")
    private String address;
    
    @Column(name="s_has_site", columnDefinition = "TINYINT UNSIGNED NOT NULL DEFAULT '0'")
    private Integer hasSite;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String updatedAt;
    
    @OneToMany(mappedBy="shop", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Employee> employees;
}
