package hw04.task1.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="users")
public class User implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086035L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="u_id", columnDefinition = "INT UNSIGNED")
    private Integer id;
    
    @Column(name="u_email", columnDefinition = "VARCHAR(50) NOT NULL")
    private String email;
    
    @Column(name="u_password", columnDefinition = "VARCHAR(50) NOT NULL")
    private String password;
    
    @Column(name="u_first_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String firstName;
    
    @Column(name="u_last_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String lastName;
    
    @Column(name="u_gender", columnDefinition = "VARCHAR(10) NOT NULL")
    private String gender;
    
    @Column(name="u_age", columnDefinition = "TINYINT UNSIGNED NOT NULL")
    private Integer age;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;
    
    @Column(name="updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'")
    private String updatedAt;
    
    public static User build(Map<String, String> params) {
        return User.builder()
                .email(params.get("email"))
                .password(params.get("password"))
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .gender(params.get("gender"))
                .age(Integer.parseInt(params.get("age")))
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .updatedAt("0000-00-00 00:00:00")
                .build();
    }
    
    public static User build(Map<String, String> params, Integer id) {
        return User.builder()
                .id(id)
                .email(params.get("email"))
                .password(params.get("password"))
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .gender(params.get("gender"))
                .age(Integer.parseInt(params.get("age")))
                .updatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();
    }
    
    @Override
    public String toString() {
        return String.format("║ %-3s ┆ %-35s ┆ %-15s ┆ %-10s ┆ %-12s ┆ %-8s ┆ %-3s ║",
                id, email, password, firstName, lastName, gender, age);
    }
}
