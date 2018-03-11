package ch.globaz.tmmas.indexationsearchservice.infrastructure.jms;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.IndexRepository;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.DossierDocument;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.DossierDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import java.io.IOException;


public class IndexationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexationListener.class);


    @Autowired
    IndexRepository indexRepository;

    @Autowired
    ObjectMapper mapper;

    @JmsListener(destination = "rentes.q",containerFactory = "jmsListenerContainerFactory")
    @JmsListener(destination = "personnes.q",containerFactory = "jmsListenerContainerFactory")
    public void onMessageAll(String rente) {
        LOGGER.info("received message='{}'", rente);



        try {

            DossierDto dto = mapper.readValue(rente,DossierDto.class);

            indexRepository.save(DossierDocument.fromDto(dto));

            LOGGER.info("Successfully indexed'{}'", rente);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



    @JmsListener(id="dlq.q",destination = "DLQ.*.*",containerFactory = "jmsListenerContainerFactory")
    public void onMessageDLQ(javax.jms.Message message) {

        LOGGER.info("received message FROM DLQ ='{}'", message);


    }


}
