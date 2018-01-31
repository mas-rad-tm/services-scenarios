package ch.globaz.springbackend.domain.repository;

import ch.globaz.springbackend.domain.model.HelloWorld;

import java.util.List;

public interface HelloWorldRepository {

	HelloWorld store (HelloWorld hw);


	List<HelloWorld> getAll();
}
