package ch.globaz.tmmas.personnes.domain.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class PersonnePhysiqueTest {

    @Test
    public void assertThatDAteAsStringCoorectImplments() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        PersonnePhysique pp = PersonnePhysique.builder("Seb","Chevre", LocalDate.parse("11.09.1978",formatter));

        assertTrue(pp != null);

        System.out.println(pp.dateNaissance().getMonth());
    }
}