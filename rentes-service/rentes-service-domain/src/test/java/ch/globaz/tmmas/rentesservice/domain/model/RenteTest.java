package ch.globaz.tmmas.rentesservice.domain.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class RenteTest {

	@Test
	public void assertIfBuilderworkCorrectly () {

		Rente rente = Rente.builder("79876",1L,LocalDate.now());
		assertThat(rente).isNotNull();
		assertThat(rente.numero()).isNotNull();
		assertThat(rente.dateEnregistrement()).isNotNull();
	}

}