package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.application.service.RenteService;
import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import ch.globaz.tmmas.rentesservice.domain.repository.RenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RenteServiceImpl implements RenteService {

	@Autowired
    RenteRepository repository;

	@Override
	public Rente sauve(Rente rente) {

		return repository.store(rente);
	}

	@Override
	public List<Rente> getAll() {
		return repository.getAll();
	}

	@Override
	public Optional getById(Long id) {

		Optional<Rente> pp = repository.getById(id);

		return pp;
	}
}
