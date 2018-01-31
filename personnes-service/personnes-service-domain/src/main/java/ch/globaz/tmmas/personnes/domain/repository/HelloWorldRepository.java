package ch.globaz.tmmas.personnes.domain.repository;

import ch.globaz.tmmas.personnes.domain.model.HelloWorld;

import java.util.List;

public interface HelloWorldRepository {

	HelloWorld store (HelloWorld hw);


	List<HelloWorld> getAll();
}
