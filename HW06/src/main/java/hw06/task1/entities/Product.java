package hw06.task1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

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
@Table(name="products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @NotEmpty(message = "Name cannot be empty!")
    @Column(name="p_name", columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;
    
    @Positive
    @Column(name="p_price", columnDefinition = "FLOAT NOT NULL")
    private Float price;
    
    @Column(name="p_description", columnDefinition = "TEXT DEFAULT NULL")
    private String description;
    
    @Size(min = 4, max = 4, message = "Manufactured year consists of 4 numbers!")
    @Column(name="p_manufactured", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Integer manufactured;
    
    @Size(min = 4, max = 4, message = "UseBefore year consists of 4 numbers!")
    @Column(name="p_use_before", columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    private Integer useBefore;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    @ManyToOne
    @JoinColumn(name="f_country_id", columnDefinition = "TINYINT UNSIGNED NOT NULL")
    private Country country;
    
    public static Product build(Product params) {
        return Product.builder()
                .name(params.getName())
                .price(params.getPrice())
                .description(params.getDescription())
                .manufactured(params.getManufactured())
                .useBefore(params.getUseBefore())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .country(params.getCountry())
                .build();
    }
    
    public static Product build(Integer id, Product params) {
        return Product.builder()
                .id(id)
                .name(params.getName())
                .price(params.getPrice())
                .description(params.getDescription())
                .manufactured(params.getManufactured())
                .useBefore(params.getUseBefore())
                .createdAt(params.getCreatedAt())
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .country(params.getCountry())
                .build();
    }
}
