package ch.globaz.tmmas.rentesservice.infrastructure.dto;

import ch.globaz.tmmas.rentesservice.infrastructure.dto.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.localdate.LocalDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
public class RenteDto {

	private String numero;
	private Long requerantId;
	private Long id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public RenteDto(){}


	private RenteDto(Long id,String numero, Long requerantId, String dateEnregistrement){
		this.id = id;
		this.numero = numero;
		this.requerantId = requerantId;
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

	public Long getId() {
		return id;
	}

	public static RenteDto fromEntity(Rente rente){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new RenteDto(rente.id(),rente.numero(), rente.requerantId(), rente.dateEnregistrement().format
				(formatter));
	}
}
