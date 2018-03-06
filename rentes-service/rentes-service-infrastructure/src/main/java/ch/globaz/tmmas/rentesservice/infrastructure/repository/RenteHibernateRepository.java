package ch.globaz.tmmas.rentesservice.infrastructure.repository;

import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import ch.globaz.tmmas.rentesservice.domain.repository.RenteRepository;
import ch.globaz.tmmas.rentesservice.infrastructure.jms.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RenteHibernateRepository extends HibernateRepository implements RenteRepository {

	@Autowired
	MessageSender jmsSender;

	@Transactional
	@Override
	public Rente store(Rente rente) {

		getSession().saveOrUpdate(rente);

		jmsSender.sendMessage(rente.toString());
		return rente;
	}

	@Transactional
	@Override
	public List<Rente> getAll() {
		return getSession().createQuery("FROM " + Rente.class.getSimpleName()).list();
	}


}
