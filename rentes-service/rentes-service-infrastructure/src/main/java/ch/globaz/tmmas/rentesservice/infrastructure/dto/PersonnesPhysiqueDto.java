package ch.globaz.tmmas.rentesservice.infrastructure.dto;


import ch.globaz.tmmas.rentesservice.infrastructure.dto.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.localdate.LocalDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.model.PersonnePhysique;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
public class PersonnesPhysiqueDto extends ResourceSupport{

	private String nom;
	private String prenom;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateNaissance;

	private String nss;


	@JsonProperty("id")
	private Long technicalId;


	public PersonnesPhysiqueDto(){}


	private PersonnesPhysiqueDto(Long id,String nom, String prenom, String dateNaissance, String nss){

		this.nom = nom;
		this.prenom = prenom;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateNaissance = LocalDate.parse(dateNaissance,formatter);
		this.nss = nss;
		this.technicalId = id;
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

	public String getNss () {
		return nss;
	}

	public Long getTechnicalId(){
		return technicalId;
	}

	public static PersonnesPhysiqueDto fromEntity(PersonnePhysique pp){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new PersonnesPhysiqueDto(pp.getId(),pp.nom(), pp.prenom(), pp.dateNaissance().format(formatter), pp
				.getNss());
	}
}
