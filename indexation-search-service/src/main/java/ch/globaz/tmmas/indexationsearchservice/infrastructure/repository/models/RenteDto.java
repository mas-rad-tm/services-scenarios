package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Document(indexName = "rente", type = "avs")
public class RenteDto {

	@Id
	private String numero;
	private Long requerantId;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public RenteDto(){}


	public RenteDto(String numero, Long requerantId, String dateEnregistrement){

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


	public static RenteDto newInstance(){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new RenteDto("12345", 1L, LocalDate.now().format(formatter));
	}
}
