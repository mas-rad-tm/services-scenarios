package ch.globaz.tmmas.personnes.application.api.dto;

import ch.globaz.tmmas.personnes.application.api.dto.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.personnes.application.api.dto.localdate.LocalDateSerializer;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
public class PersonnesPhysiqueDto {

	private String nom;
	private String prenom;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateNaissance;


	public PersonnesPhysiqueDto(){}


	private PersonnesPhysiqueDto(String nom, String prenom, String dateNaissance){

		this.nom = nom;
		this.prenom = prenom;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateNaissance = LocalDate.parse(dateNaissance,formatter);
	}


	public String getNom() {
		return nom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public String getPrenom () {
		return prenom;
	}

	public static PersonnesPhysiqueDto fromEntity(PersonnePhysique pp){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new PersonnesPhysiqueDto(pp.nom(), pp.prenom(), pp.dateNaissance().format(formatter));
	}
}
