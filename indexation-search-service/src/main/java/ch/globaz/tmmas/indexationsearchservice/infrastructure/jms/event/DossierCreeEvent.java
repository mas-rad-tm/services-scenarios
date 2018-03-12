package ch.globaz.tmmas.indexationsearchservice.infrastructure.jms.event;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.ValueObject;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.dossier.Dossier;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DossierCreeEvent implements ValueObject<DossierCreeEvent> {

    private Dossier dossier;


    public DossierCreeEvent(Dossier dossier) {
        this.dossier = dossier;
    }

    public Dossier dossier() {
        return dossier;
    }

    @Override
    public boolean sameValueAs(DossierCreeEvent other) {
        return this.equals(other);

    }
}
