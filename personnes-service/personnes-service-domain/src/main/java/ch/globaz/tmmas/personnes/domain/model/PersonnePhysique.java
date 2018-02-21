package ch.globaz.tmmas.personnes.domain.model;

import lombok.ToString;

import java.time.LocalDate;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
@ToString
public class PersonnePhysique {

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    public PersonnePhysique(String nom, String prenom, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public String nom() {
        return nom;
    }

    public String prenom() {
        return prenom;
    }

    public LocalDate dateNaissance() {
        return dateNaissance;
    }

    public static PersonnePhysique builder(String nom, String prenom, LocalDate dateNaissance) {
        return new PersonnePhysique(nom,prenom,dateNaissance);
    }

    //hibernate
    private Long id;

    PersonnePhysique(){}
}
