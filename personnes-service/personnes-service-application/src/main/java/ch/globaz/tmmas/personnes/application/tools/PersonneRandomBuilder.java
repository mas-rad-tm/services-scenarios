package ch.globaz.tmmas.personnes.application.tools;

import ch.globaz.tmmas.personnes.domain.model.NSS;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class PersonneRandomBuilder {

    static final Faker faker = new Faker(new Locale("fr"));

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public static PersonnePhysique personnePhysique() {

            return PersonnePhysique.builder(nom(),prenom(),dateNaissance(),new NSS(nss()));
    }

    private static String nom () {
        return faker.name().lastName();
    }

    private static String prenom () {
        return faker.name().firstName();
    }

    private static LocalDate dateNaissance () {

        Date date = faker.date().birthday(15,80);

        return LocalDate.parse(df.format(date),formatter);
    }

    private static String nss () {

        StringBuilder nss = new StringBuilder("756.");
        nss.append(faker.number().randomDigit());
        nss.append(faker.number().randomDigit());
        nss.append(faker.number().randomDigit());
        nss.append(faker.number().randomDigit());
        nss.append(".");
        nss.append(faker.number().randomDigit());
        nss.append(faker.number().randomDigit());
        nss.append(faker.number().randomDigit());
        nss.append(faker.number().randomDigit());
        nss.append(".");
        nss.append(faker.number().randomDigit());
        nss.append(faker.number().randomDigit());

        return nss.toString();

    }
}
