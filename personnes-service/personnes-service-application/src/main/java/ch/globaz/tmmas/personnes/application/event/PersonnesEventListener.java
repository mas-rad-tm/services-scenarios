package ch.globaz.tmmas.personnes.application.event;


import ch.globaz.tmmas.personnes.domain.event.PersonnesPhysiqueCreeEvent;
import ch.globaz.tmmas.personnes.domain.event.RandomPersonGeneratedEvent;
import ch.globaz.tmmas.personnes.domain.notification.NotificationService;
import ch.globaz.tmmas.personnes.domain.repository.PersonnePhysiqueRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PersonnesEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonnesEventListener.class);

    @Autowired
    NotificationService notificationService;

    @Autowired
    PersonnePhysiqueRepository repository;

    @Autowired
    ObjectMapper mapper;

    @EventListener
    void personneCreeEventListener(PersonnesPhysiqueCreeEvent event) throws JsonProcessingException {

        LOGGER.info("PersonnePhysiqueCree Event {}",event);

        notificationService.notify(mapper.writeValueAsString(event));

    }

    @EventListener
    void randomPersonnesCreeEventListener(RandomPersonGeneratedEvent event) throws JsonProcessingException {

        LOGGER.info("RandomPersonGeneratedEvent Event {}",event);

        repository.storeAndFlush(event.getPersonnes());

    }
}
