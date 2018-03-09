package ch.globaz.tmmas.personnes.application.api.web;

import ch.globaz.tmmas.personnes.application.api.dto.PersonnesPhysiqueDto;
import ch.globaz.tmmas.personnes.application.service.PersonnePhysiqueService;
import ch.globaz.tmmas.personnes.domain.model.NSS;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

		PersonnePhysique pp = personneService.sauve(PersonnePhysique.builder(dto.getNom(),dto.getPrenom(),dto
				.getDateNaissance(),new NSS(dto.getNss())));

		LOGGER.debug("createPerson() return  {}",pp);

		return new ResponseEntity<>(PersonnesPhysiqueDto.fromEntity(pp), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{personneId}")
	public ResponseEntity getPersonnById(@PathVariable Long personneId){

		LOGGER.debug("getPersonById(), {}",personneId);

		Optional<PersonnePhysique> pp = personneService.getById(personneId);

		if(pp.isPresent()){
			PersonnesPhysiqueDto dto = PersonnesPhysiqueDto.fromEntity(pp.get());

			LOGGER.debug("getPersonById() return  {}",dto);

			return new ResponseEntity<>(dto, HttpStatus.OK);
		}else{

			return new ResponseEntity<>("No entity found with id " + personneId, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PersonnesPhysiqueDto>> getHello(@RequestParam(value = "countOnly", required = false)
			                                                               String countOnlyValue){

		Boolean countOnly = Boolean.valueOf(countOnlyValue);
		List<PersonnePhysique> hws;
		HttpHeaders headers = new HttpHeaders();
		Long numberOfEntries = personneService.countPersonnePhysique();

		headers.add("X-Total-Count", String.valueOf(numberOfEntries));

		if(countOnly){
			hws = new ArrayList<>();

		}else{
			hws = personneService.getAll();
		}



		return new ResponseEntity<>(getAllAsDto(hws),headers, HttpStatus.OK);
	}

	private List<PersonnesPhysiqueDto> getAllAsDto (List<PersonnePhysique> ppList) {
		return ppList.stream().map(PersonnesPhysiqueDto::fromEntity).collect(Collectors.toList());
	}


}
