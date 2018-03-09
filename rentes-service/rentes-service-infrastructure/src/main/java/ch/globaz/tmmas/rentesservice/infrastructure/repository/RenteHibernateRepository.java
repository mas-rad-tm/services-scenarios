package ch.globaz.tmmas.rentesservice.infrastructure.repository;

import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import ch.globaz.tmmas.rentesservice.domain.repository.RenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class RenteHibernateRepository extends HibernateRepository implements RenteRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(RenteHibernateRepository.class);

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

	@Transactional
	@Override
	public Optional<Rente> getById(Long renteId) {

		LOGGER.debug("{}#getBiyId, renteId:{}",this.getClass().getName(),renteId);

		Optional<Rente> rente = Optional.ofNullable(getSession().get(Rente.class,renteId));

		return rente;

	}

}
