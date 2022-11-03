package hw03.task1;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import hw03.task1.entities.OwnBean;
import hw03.task1.messages.Messages;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Main Class represents homework #3 #task1
 *
 * @author YevhenKovalevskyi
 */
@Slf4j
@SpringBootApplication
public class Main {
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
    
        @Cleanup
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        
        if (beanFactory.containsBean("ownBean")) {
            OwnBean ownBean = beanFactory.getBean("ownBean", OwnBean.class);
            ownBean.ownBeanMethod(Messages.CONDITIONS_MET.getOutMessage());
        }
        
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
}
