package ch.globaz.tmmas.rentesservice.infrastructure.repository;

import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import ch.globaz.tmmas.rentesservice.domain.repository.RenteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RenteHibernateRepository extends HibernateRepository implements RenteRepository {

	@Transactional
	@Override
	public Rente store(Rente rente) {
		getSession().saveOrUpdate(rente);
		return rente;
	}

	@Transactional
	@Override
	public List<Rente> getAll() {
		return getSession().createQuery("FROM " + Rente.class.getSimpleName()).list();
	}


}
