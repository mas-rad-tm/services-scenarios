package ch.globaz.tmmas.rentesservice.application.service;

import ch.globaz.tmmas.rentesservice.domain.model.Rente;

import java.util.List;

public interface RenteService {

	Rente sauve(Rente rente);

	List<Rente> getAll();
}
