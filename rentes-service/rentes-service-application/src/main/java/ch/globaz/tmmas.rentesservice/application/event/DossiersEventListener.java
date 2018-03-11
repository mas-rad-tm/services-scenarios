package ch.globaz.tmmas.rentesservice.application.event;


import ch.globaz.tmmas.rentesservice.domain.NotificationService;
import ch.globaz.tmmas.rentesservice.domain.DossierCreeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DossiersEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossiersEventListener.class);

    @Autowired
    NotificationService notificationService;

    @EventListener
    void rentesCreesEvent(DossierCreeEvent event){

        LOGGER.info("RenteCree Event {}",event);

        notificationService.notify(event.toString());

    }
}
