package ch.globaz.tmmas.rentesservice.application.service;

import ch.globaz.tmmas.rentesservice.domain.model.Rente;

import java.util.List;
import java.util.Optional;

public interface RenteService {

	Rente sauve(Rente rente);

	List<Rente> getAll();

    Optional getById(Long id);
}
