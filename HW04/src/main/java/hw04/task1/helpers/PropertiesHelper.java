package hw04.task1.helpers;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
public class PropertiesHelper {
    
    public static Properties getProperties(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
    
        try (InputStream is = loader.getResourceAsStream(path)) {
            props.load(is);
        } catch (IOException e) {
            log.error("{} file not found / loaded error.", path);
            e.printStackTrace();
        }
        
        return props;
    }
}
