package hw03.task1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@Data
@NoArgsConstructor
public class OwnBean implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086040L;
    
    public void ownBeanMethod(String message) {
        log.info(message);
        System.out.println(message);
    }
}
