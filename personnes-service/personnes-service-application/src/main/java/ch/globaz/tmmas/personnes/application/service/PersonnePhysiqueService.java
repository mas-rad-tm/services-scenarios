package ch.globaz.tmmas.personnes.application.service;

import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;

import java.util.List;
import java.util.Optional;

public interface PersonnePhysiqueService {

	PersonnePhysique sauve(PersonnePhysique helloWorld);

    void genereteRandomAccessDatas(Integer nbElements);

    List<PersonnePhysique> getAll();

	Long countPersonnePhysique();

	Optional getById(Long id);
}
