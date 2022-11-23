package hw08.task1.actions;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.exceptions.RequestAlreadyTakenException;
import hw08.task1.messages.Messages;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
public class RequestAction {
    
    public static String getRequestParam(HttpServletRequest request, String key) {
        Map<String, String> requestParams = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    
        return requestParams.get(key);
    }
    
    public static <T>T getRequestBody(HttpServletRequest request, ObjectMapper mapper, Class<T> objectClass) {
        try {
            return mapper.readValue(request.getInputStream(), objectClass);
        } catch (IOException e) {
            log.error(Messages.REQUEST_ALREADY_TAKEN.getLogMessage());
            throw new RequestAlreadyTakenException(Messages.REQUEST_ALREADY_TAKEN.getOutMessage());
        }
    }
}
