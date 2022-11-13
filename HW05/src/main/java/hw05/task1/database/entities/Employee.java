package hw05.task1.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="employees")
public class Employee implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086037L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="e_id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @Column(name="f_shop_id", columnDefinition = "INT UNSIGNED NOT NULL")
    private Integer shopId;
    
    @Column(name="e_email", columnDefinition = "VARCHAR(50) NOT NULL")
    private String email;
    
    @Column(name="e_first_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String firstName;
    
    @Column(name="e_last_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String lastName;
    
    @Column(name="e_gender", columnDefinition = "VARCHAR(10) NOT NULL")
    private String gender;
    
    @Column(name="e_age", columnDefinition = "TINYINT UNSIGNED NOT NULL")
    private Integer age;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    @ManyToOne(targetEntity=Shop.class, fetch = FetchType.LAZY)
    @JoinColumn(name="f_shop_id", insertable = false, updatable = false)
    @JsonBackReference
    private Shop shop;
    
    public static Employee build(Map<String, String> params) {
        return Employee.builder()
                .shopId(Integer.parseInt(params.get("shopId")))
                .email(params.get("email"))
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .gender(params.get("gender"))
                .age(Integer.parseInt(params.get("age")))
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .build();
    }
    
    public static Employee build(Map<String, String> params, Integer id) {
        return Employee.builder()
                .id(id)
                .shopId(Integer.parseInt(params.get("shopId")))
                .email(params.get("email"))
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .gender(params.get("gender"))
                .age(Integer.parseInt(params.get("age")))
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();
    }
    
    public static Employee build(Employee params) {
        return Employee.builder()
                .shopId(params.getShopId())
                .email(params.getEmail())
                .firstName(params.getFirstName())
                .lastName(params.getLastName())
                .gender(params.getGender())
                .age(params.getAge())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .build();
    }
    
    public static Employee build(Employee params, Integer id) {
        return Employee.builder()
                .id(id)
                .shopId(params.getShopId())
                .email(params.getEmail())
                .firstName(params.getFirstName())
                .lastName(params.getLastName())
                .gender(params.getGender())
                .age(params.getAge())
                .createdAt(params.getCreatedAt())
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();
    }
}
