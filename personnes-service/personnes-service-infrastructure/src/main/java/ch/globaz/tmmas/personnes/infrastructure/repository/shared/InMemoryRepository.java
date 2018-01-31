package ch.globaz.tmmas.personnes.infrastructure.repository.shared;

import ch.globaz.tmmas.personnes.domain.model.HelloWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class InMemoryRepository<T> {

	Map<Long, T> dataSource = new HashMap<>();

	public Long generateRandomId () {
		return new Random().nextLong();
	}

	public Map<Long,T> datasource() {
		return this.dataSource;
	}
}
