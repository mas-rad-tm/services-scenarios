package ch.globaz.tmmas.personnes.application.service;

import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;

import java.util.List;

public interface PersonnePhysiqueService {

	PersonnePhysique sauve(PersonnePhysique helloWorld);

	List<PersonnePhysique> getAll();
}
