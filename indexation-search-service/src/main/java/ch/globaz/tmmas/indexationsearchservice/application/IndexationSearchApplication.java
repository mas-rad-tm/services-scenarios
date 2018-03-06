package ch.globaz.tmmas.indexationsearchservice.application;

import ch.globaz.tmmas.indexationsearchservice.application.configuration.MessageListenerErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ActiveMQAutoConfiguration.class, JmsAutoConfiguration.class})

@ComponentScan(basePackages = "ch.globaz.tmmas.indexationsearchservice")
public class IndexationSearchApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexationSearchApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(IndexationSearchApplication.class);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("APPLICATION STARTED EVENT");
    }
}
