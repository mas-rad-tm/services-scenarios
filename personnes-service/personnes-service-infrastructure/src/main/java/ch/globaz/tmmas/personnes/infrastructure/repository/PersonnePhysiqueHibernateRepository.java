package ch.globaz.tmmas.personnes.infrastructure.repository;

import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import ch.globaz.tmmas.personnes.domain.repository.PersonnePhysiqueRepository;
import ch.globaz.tmmas.personnes.infrastructure.repository.shared.HibernateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class PersonnePhysiqueHibernateRepository extends HibernateRepository implements PersonnePhysiqueRepository {


	@Transactional
	@Override
	public PersonnePhysique store(PersonnePhysique pp) {
		getSession().save(pp);
		return pp;
	}

	@Transactional
	@Override
	public List<PersonnePhysique> getAll() {
		return getSession().createQuery("FROM " + PersonnePhysique.class.getSimpleName()).list();

	}

	@Transactional
	@Override
	public Long countAllTiers() {

		Long count = (Long)getSession().createQuery("SELECT COUNT(1) FROM  " + PersonnePhysique.class.getSimpleName())
				.getSingleResult();

		return count;

	}

	@Transactional
	@Override
	public PersonnePhysique getById(Long personneId) {

		PersonnePhysique pp = getSession().get(PersonnePhysique.class,personneId);

		return pp;

	}



}
