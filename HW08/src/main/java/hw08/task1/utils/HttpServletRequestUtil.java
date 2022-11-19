package hw08.task1.utils;

import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author YevhenKovalevskyi
 */
public class HttpServletRequestUtil {
    
    public static String getAttributeByKey(HttpServletRequest request, String key) {
        Map<String, String> pathVariables = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        
        return pathVariables.get(key);
    }
}
