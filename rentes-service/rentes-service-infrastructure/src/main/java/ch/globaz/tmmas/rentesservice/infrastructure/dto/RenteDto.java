package ch.globaz.tmmas.rentesservice.infrastructure.dto;

import ch.globaz.tmmas.rentesservice.infrastructure.dto.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.localdate.LocalDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
public class RenteDto extends ResourceSupport {

	private String numero;
	private Long requerantId;
	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public RenteDto(){}


	private RenteDto(Long id,String numero, Long requerantId, String dateEnregistrement){

		this.numero = numero;
		this.requerantId = requerantId;
		this.technicalId = id;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
	}


	public String getNumero() {
		return numero;
	}

	public LocalDate getDateEnregistrement() {
		return dateEnregistrement;
	}

	public Long getRequerantId () {
		return requerantId;
	}

	public Long getTechnicalId(){
		return technicalId;
	}

	public static RenteDto fromEntity(Rente rente){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new RenteDto(rente.id(),
				rente.numero(), rente.requerantId(),
				rente.dateEnregistrement().format(formatter));
	}
}
