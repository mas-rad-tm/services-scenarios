package ch.globaz.tmmas.personnes.domain.repository;

import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;

import java.util.List;

public interface PersonnePhysiqueRepository {

	PersonnePhysique store(PersonnePhysique pp);


	List<PersonnePhysique> getAll();
}
