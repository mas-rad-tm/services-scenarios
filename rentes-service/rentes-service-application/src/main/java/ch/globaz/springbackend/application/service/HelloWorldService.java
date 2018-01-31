package ch.globaz.springbackend.application.service;

import ch.globaz.springbackend.domain.model.HelloWorld;

import java.util.List;

public interface HelloWorldService {

	HelloWorld sauve(HelloWorld helloWorld);

	List<HelloWorld> getAll();
}
