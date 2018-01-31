package ch.globaz.tmmas.personnes.infrastructure.repository;

import ch.globaz.tmmas.personnes.domain.model.HelloWorld;
import ch.globaz.tmmas.personnes.domain.repository.HelloWorldRepository;
import ch.globaz.tmmas.personnes.infrastructure.repository.shared.HibernateRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("hibernate")
@Component
public class HelloWorldHibernateRepository extends HibernateRepository implements HelloWorldRepository {

	@Transactional
	@Override
	public HelloWorld store(HelloWorld hw) {
		getSession().saveOrUpdate(hw);
		return hw;
	}

	@Transactional
	@Override
	public List<HelloWorld> getAll() {
		return getSession().createQuery("FROM " + HelloWorld.class.getSimpleName()).list();
	}


}
