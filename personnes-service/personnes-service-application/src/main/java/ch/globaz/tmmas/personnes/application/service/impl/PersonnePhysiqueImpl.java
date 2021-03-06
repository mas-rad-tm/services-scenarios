package ch.globaz.tmmas.personnes.application.service.impl;

import ch.globaz.tmmas.personnes.application.service.PersonnePhysiqueService;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import ch.globaz.tmmas.personnes.domain.repository.PersonnePhysiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
@Component
public class PersonnePhysiqueImpl implements PersonnePhysiqueService {

    @Autowired
    PersonnePhysiqueRepository repository;

    @Override
    public PersonnePhysique sauve(PersonnePhysique pp) {
        return repository.store(pp);
    }

    @Override
    public List<PersonnePhysique> getAll() {
        return repository.getAll();
    }
}
