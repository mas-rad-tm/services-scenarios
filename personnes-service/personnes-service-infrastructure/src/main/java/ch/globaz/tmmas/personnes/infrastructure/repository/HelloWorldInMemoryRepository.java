package ch.globaz.tmmas.personnes.infrastructure.repository;

import ch.globaz.tmmas.personnes.domain.model.HelloWorld;
import ch.globaz.tmmas.personnes.domain.repository.HelloWorldRepository;
import ch.globaz.tmmas.personnes.infrastructure.repository.shared.InMemoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;


@Component
@Profile("inMemory")
public class HelloWorldInMemoryRepository extends InMemoryRepository<HelloWorld> implements HelloWorldRepository{




	@Override
	public HelloWorld store(HelloWorld hw) {

		Long id = generateRandomId();

		hw.id(id);

		datasource().putIfAbsent(id,hw);

		return hw;

	}

	@Override
	public List<HelloWorld> getAll() {
		return datasource().values().stream().collect(Collectors.toList());
	}
}
