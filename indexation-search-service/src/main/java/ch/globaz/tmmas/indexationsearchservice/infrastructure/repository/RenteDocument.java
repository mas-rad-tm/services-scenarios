package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.RenteDto;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Document(indexName = "rente", type = "avs")
public class RenteDocument {


	private String numero;
	private Long requerantId;
	@Id
	private Long id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public RenteDocument(){}


	public RenteDocument(Long id,String numero, Long requerantId, String dateEnregistrement){

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


	public static RenteDocument fromDto(RenteDto renteDto){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new RenteDocument(renteDto.getId(),renteDto.getNumero(), renteDto.getRequerantId(), renteDto
				.getDateEnregistrement()
				.format(formatter));
	}
}
