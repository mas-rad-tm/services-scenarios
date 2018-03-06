package ch.globaz.tmmas.indexationsearchservice.infrastructure.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.MessageEOFException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MessageListener;

@Component
public class JMSListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSListener.class);


    @JmsListener(destination = "*.*",containerFactory = "jmsListenerContainerFactory")
    public void onMessageAll(javax.jms.Message message) {
        LOGGER.info("received message='{}'", message);
        throw new NullPointerException();

    }

    @JmsListener(destination = "DLQ.*.*",containerFactory = "jmsListenerContainerFactory")
    public void onMessageDLQ(javax.jms.Message message) {
        LOGGER.info("received message FROM DLQ ='{}'", message);
    }


}
