package ch.globaz.tmmas.personnes.application.service.impl;

import ch.globaz.tmmas.personnes.application.service.PersonnePhysiqueService;
import ch.globaz.tmmas.personnes.application.tools.PersonneRandomBuilder;
import ch.globaz.tmmas.personnes.domain.event.RandomPersonGeneratedEvent;
import ch.globaz.tmmas.personnes.domain.model.NSS;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import ch.globaz.tmmas.personnes.domain.repository.PersonnePhysiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
@Component
public class PersonnePhysiqueServiceImpl implements PersonnePhysiqueService {

    @Autowired
    PersonnePhysiqueRepository repository;

    @Autowired
    ApplicationEventPublisher publisher;



    @Override
    public PersonnePhysique sauve(PersonnePhysique pp) {

        return repository.store(pp);
    }


    @Override
    public void genereteRandomAccessDatas(Integer nbElements){

        List<PersonnePhysique> personnePhysiqueBatchList = new ArrayList<>(20);

        IntStream.range(0,nbElements).forEach(iteration -> {
            if(personnePhysiqueBatchList.size() == 20){
                publisher.publishEvent(new RandomPersonGeneratedEvent(personnePhysiqueBatchList));
                personnePhysiqueBatchList.clear();
            }
            personnePhysiqueBatchList.add(PersonneRandomBuilder.personnePhysique());
        });
    }

    @Override
    public List<PersonnePhysique> getAll() {
        return repository.getAll();
    }

    @Override
    public Long countPersonnePhysique() {
        return repository.countAllTiers();
    }

    @Override
    public Optional getById(Long id) {

        Optional<PersonnePhysique> pp = repository.getById(id);

        return pp;
    }
}
