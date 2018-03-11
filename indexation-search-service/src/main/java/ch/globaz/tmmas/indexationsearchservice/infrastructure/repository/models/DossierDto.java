package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DossierDto {


	private String numero;
	private Long requerantId;
	private Long id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public DossierDto(){}


	private DossierDto(Long id, String numero, Long requerantId, String dateEnregistrement){

		this.numero = numero;
		this.requerantId = requerantId;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
	}


	public String getNumero() {
		return numero;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDateEnregistrement() {
		return dateEnregistrement;
	}

	public Long getRequerantId () {
		return requerantId;
	}


}
