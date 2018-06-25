package ch.globaz.tmmas.personnes.domain.repository;

import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonnePhysiqueRepository {

	PersonnePhysique store(PersonnePhysique pp);


    @Transactional
    void storeAndFlush(List<PersonnePhysique> pps);

    List<PersonnePhysique> getAll();

	Long countAllTiers();

	Optional<PersonnePhysique> getById(Long personneId);
}
