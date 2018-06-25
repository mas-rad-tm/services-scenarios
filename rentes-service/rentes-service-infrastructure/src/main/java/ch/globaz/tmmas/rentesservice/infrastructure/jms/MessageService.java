package ch.globaz.tmmas.rentesservice.infrastructure.jms;


import ch.globaz.tmmas.rentesservice.domain.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService implements NotificationService {

    @Autowired
    MessageSender jmsSender;

    @Override
    public void notify(String msg) {
        jmsSender.sendMessage(msg);
    }
}
