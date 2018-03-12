package ch.globaz.tmmas.rentesservice.domain.event;

import ch.globaz.tmmas.rentesservice.domain.ValueObject;
import ch.globaz.tmmas.rentesservice.domain.model.Dossier;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DossierCreeEvent implements ValueObject<DossierCreeEvent> {

    private Dossier dossier;


    public DossierCreeEvent(Dossier dossier) {
        this.dossier = dossier;
    }

    public Dossier getDossier() {
        return dossier;
    }

    @Override
    public boolean sameValueAs(DossierCreeEvent other) {
        return this.equals(other);

    }
}
