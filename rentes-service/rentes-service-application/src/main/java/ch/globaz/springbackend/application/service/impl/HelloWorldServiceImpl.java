package ch.globaz.springbackend.application.service.impl;

import ch.globaz.springbackend.application.service.HelloWorldService;
import ch.globaz.springbackend.domain.model.HelloWorld;
import ch.globaz.springbackend.domain.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
