package hw06.task1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @Column(name="p_name", columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;
    
    @Column(name="p_description", columnDefinition = "TEXT DEFAULT NULL")
    private String description;
    
    @Positive
    @Column(name="p_price", columnDefinition = "FLOAT NOT NULL")
    private BigDecimal price;
    
    @Size(min = 4, max = 4, message = "Manufactured year consists of 4 numbers!")
    @Column(name="p_manufactured", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Integer manufactured;
    
    @Size(min = 4, max = 4, message = "UseBefore year consists of 4 numbers!")
    @Column(name="p_use_before", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Integer useBefore;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name="f_country_id", columnDefinition = "TINYINT UNSIGNED NOT NULL")
    private Country country;
}
