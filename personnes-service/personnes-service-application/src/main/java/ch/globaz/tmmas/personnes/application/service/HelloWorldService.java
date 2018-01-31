package ch.globaz.tmmas.personnes.application.service;

import ch.globaz.tmmas.personnes.domain.model.HelloWorld;

import java.util.List;

public interface HelloWorldService {

	HelloWorld sauve(HelloWorld helloWorld);

	List<HelloWorld> getAll();
}
