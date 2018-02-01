package ch.globaz.tmmas.personnes.application.api.web;

import ch.globaz.tmmas.personnes.application.api.dto.PersonnesPhysiqueDto;
import ch.globaz.tmmas.personnes.application.service.PersonnePhysiqueService;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personnes")
public class PersonnesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonnesController.class);

	@Autowired
	PersonnePhysiqueService personneService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PersonnesPhysiqueDto> createPerson(@RequestBody PersonnesPhysiqueDto dto){
		LOGGER.debug("createPerson(), {}",dto);

		PersonnePhysique pp = personneService.sauve(PersonnePhysique.builder(dto.getNom(),dto.getPrenom(),dto.getDateNaissance()));

		LOGGER.debug("createPerson() return  {}",pp);

		return new ResponseEntity<>(PersonnesPhysiqueDto.fromEntity(pp), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PersonnesPhysiqueDto>> getHello(){

		List<PersonnePhysique> hws = personneService.getAll();

		return new ResponseEntity<>(getAllAsDto(hws), HttpStatus.OK);
	}

	private List<PersonnesPhysiqueDto> getAllAsDto (List<PersonnePhysique> ppList) {
		return ppList.stream().map(PersonnesPhysiqueDto::fromEntity).collect(Collectors.toList());
	}


}
