package ch.globaz.tmmas.indexationsearchservice.infrastructure.jms;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.IndexRepository;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.RenteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.MessageEOFException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import java.time.format.DateTimeFormatter;

@Component
public class JMSListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSListener.class);


    @Autowired
    IndexRepository indexRepository;

    @JmsListener(destination = "*.*",containerFactory = "jmsListenerContainerFactory")
    public void onMessageAll(javax.jms.Message message) {
        LOGGER.info("received message='{}'", message);


        indexRepository.save(RenteDto.newInstance());

    }

    @JmsListener(destination = "DLQ.*.*",containerFactory = "jmsListenerContainerFactory")
    public void onMessageDLQ(javax.jms.Message message) {
        LOGGER.info("received message FROM DLQ ='{}'", message);
    }


}
