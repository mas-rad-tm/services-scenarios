package ch.globaz.tmmas.rentesservice.application.api.web;

import ch.globaz.tmmas.rentesservice.infrastructure.dto.PersonnesPhysiqueDto;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.RenteDto;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.RenteEtendueDto;
import ch.globaz.tmmas.rentesservice.infrastructure.service.FeignPersonnesPhysiqueService;
import ch.globaz.tmmas.rentesservice.application.service.RenteService;
import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/rentes")
public class RentesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentesController.class);

	private static final String RENTES = "/rentes";
	private static final String RENTE = RENTES + "/{id}";

	@Autowired
	RenteService renteService;

	@Autowired
	FeignPersonnesPhysiqueService personnePhysiqueService;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity createRente(@RequestBody RenteDto dto){

		LOGGER.debug("createRente(), {}",dto);

		ResponseEntity<PersonnesPhysiqueDto> personne
                = personnePhysiqueService.getPersonnePhysiqueById(dto.getRequerantId());

		if(personne.getStatusCode().equals(HttpStatus.NOT_FOUND)){

		    LOGGER.debug("createRente(), no person with this id: {}",dto.getRequerantId());

			return new ResponseEntity<String>("Tiers referenced not exist: " + dto.getRequerantId(), HttpStatus
					.CONFLICT);
		}else{

		    LOGGER.debug("createRente(), find personne : {}",personne.getBody());

			Rente rente = renteService.sauve(Rente.builder(dto.getNumero(),
					Long.valueOf(dto.getRequerantId()),
					dto.getDateEnregistrement()));

			RenteDto rdto = RenteDto.fromEntity(rente);

			rdto.add(linkTo(methodOn(RentesController.class).getRenteById(rdto.getTechnicalId())).withSelfRel());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new UriTemplate(RENTE).expand(rdto.getTechnicalId()));


			return new ResponseEntity<>(rdto, HttpStatus.CREATED);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllRentes(@RequestParam(value = "etendu", required = false)
			                                            String etendu){
		Boolean renteEtendu = Boolean.valueOf(etendu);

		List<Rente> rentes = renteService.getAll();


		if(renteEtendu){
			List<RenteEtendueDto> rentesEtendues = getAllAsDto(rentes).stream().map(rente -> {

				rente.add(linkTo(methodOn(RentesController.class)
						.getRenteById(rente.getTechnicalId())).withSelfRel());

				//recup pp dto
				PersonnesPhysiqueDto ppDto =
						personnePhysiqueService.getPersonnePhysiqueById(rente.getRequerantId()).getBody();

				ppDto.add(linkTo(methodOn(FeignPersonnesPhysiqueService.class)
						.getPersonnePhysiqueById(ppDto.getTechnicalId())).withSelfRel());

				return new RenteEtendueDto(rente, ppDto);

			}).collect(Collectors.toList());

			return new ResponseEntity<>(rentesEtendues, HttpStatus.OK);
		}


		return new ResponseEntity<>(getAllAsDto(rentes), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{personneId}")
	public ResponseEntity getRenteById(@PathVariable Long personneId){

		LOGGER.debug("getRenteById(), {}",personneId);

		Optional<Rente> rente = renteService.getById(personneId);

		if(rente.isPresent()){
			RenteDto dto = RenteDto.fromEntity(rente.get());

			dto.add(linkTo(methodOn(RentesController.class).getRenteById(dto.getTechnicalId())).withSelfRel());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new UriTemplate(RENTE).expand(dto.getTechnicalId()));


			LOGGER.debug("getPersonById() return  {}",dto);

			return new ResponseEntity<>(dto, HttpStatus.OK);
		}else{

			return new ResponseEntity<>("No entity found with id " + personneId, HttpStatus.NOT_FOUND);
		}

	}

	private List<RenteDto> getAllAsDto (List<Rente> renteList) {
		return renteList.stream().map(RenteDto::fromEntity).collect(Collectors.toList());
	}


}
