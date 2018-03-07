package ch.globaz.tmmas.rentesservice.infrastructure.repository;

import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import ch.globaz.tmmas.rentesservice.domain.repository.RenteRepository;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.RenteDto;
import ch.globaz.tmmas.rentesservice.infrastructure.jms.MessageSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RenteHibernateRepository extends HibernateRepository implements RenteRepository {

	@Autowired
	MessageSender jmsSender;

	@Autowired
	ObjectMapper mapper;

	@Transactional
	@Override
	public Rente store(Rente rente) {

		getSession().saveOrUpdate(rente);

		try {
			String rentestr = mapper.writeValueAsString(RenteDto.fromEntity(rente));
			jmsSender.sendMessage(rentestr);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


		return rente;
	}

	@Transactional
	@Override
	public List<Rente> getAll() {
		return getSession().createQuery("FROM " + Rente.class.getSimpleName()).list();
	}


}
