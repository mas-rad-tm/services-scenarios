package ch.globaz.tmmas.rentesservice.domain.repository;

import ch.globaz.tmmas.rentesservice.domain.model.Rente;

import java.util.List;
import java.util.Optional;

public interface RenteRepository {

	Rente store (Rente rente);


	List<Rente> getAll();


    Optional<Rente> getById(Long renteId);
}
