package ch.globaz.tmmas.personnes.domain.event;

import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import lombok.Getter;

import java.util.List;

@Getter
public class RandomPersonGeneratedEvent {

    List<PersonnePhysique> personnes;

    public RandomPersonGeneratedEvent(List<PersonnePhysique> personnePhysique){
        this.personnes = personnePhysique;
    }
}
