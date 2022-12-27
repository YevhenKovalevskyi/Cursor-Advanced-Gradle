package hw10.task1.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YevhenKovalevskyi
 */
@Configuration
public class ProjectConfiguration {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
