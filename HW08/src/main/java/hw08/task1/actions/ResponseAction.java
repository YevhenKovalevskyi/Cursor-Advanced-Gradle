package hw08.task1.actions;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.exceptions.PrintWriterException;
import hw08.task1.messages.Messages;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
public class ResponseAction {
    
    private static final String TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    public static void setResponse(
            HttpServletResponse response, ObjectMapper mapper, HttpStatus status, Object result
    ) {
        try {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("status", status.name().toLowerCase());
            body.put("code", status.value());
            body.put("timestamp", TIMESTAMP);
            body.put("result", result);
            
            @Cleanup PrintWriter out = response.getWriter();
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(mapper.writeValueAsString(body));
            out.flush();
        } catch (IOException e) {
            log.error(Messages.PRINT_WRITER_ERROR.getLogMessage(), e.getMessage());
            throw new PrintWriterException(String.format(Messages.PRINT_WRITER_ERROR.getOutMessage(), e.getMessage()));
        }
    }
}
