package hw03.task1.configurations;

import hw03.task1.entities.OwnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author YevhenKovalevskyi
 */
@Configuration
@ComponentScan("hw03.task1.entities")
@ConditionalOnClass(name="hw03.task1.conditions.ConfigurationEnabler")
@PropertySource("classpath:task1/application.properties")
public class OwnAutoConfiguration {
    
    @Bean
    @ConditionalOnProperty(name="custom.configuration.enabled", havingValue="true")
    public OwnBean ownBean() {
        return new OwnBean();
    }
}
