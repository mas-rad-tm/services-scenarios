package ch.globaz.tmmas.personnes.application.service.impl;

import ch.globaz.tmmas.personnes.application.service.HelloWorldService;
import ch.globaz.tmmas.personnes.domain.model.HelloWorld;
import ch.globaz.tmmas.personnes.domain.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelloWorldServiceImpl implements HelloWorldService {

	@Autowired
	HelloWorldRepository repository;

	@Override
	public HelloWorld sauve(HelloWorld helloWorld) {

		return repository.store(helloWorld);
	}

	@Override
	public List<HelloWorld> getAll() {
		return repository.getAll();
	}
}
