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
    private String nss;

    public PersonnePhysique(String nom, String prenom, LocalDate dateNaissance, String nss) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nss = nss;
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

    public String getNss () {
        return nss;
    }

    public static PersonnePhysique builder(String nom, String prenom, LocalDate dateNaissance, String nss) {
        return new PersonnePhysique(nom,prenom,dateNaissance,nss);
    }

    //hibernate
    private Long id;

    PersonnePhysique(){}
}
