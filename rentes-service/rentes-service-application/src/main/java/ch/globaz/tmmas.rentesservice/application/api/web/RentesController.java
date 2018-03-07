package ch.globaz.tmmas.rentesservice.application.api.web;

import ch.globaz.tmmas.rentesservice.infrastructure.dto.PersonnesPhysiqueDto;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.RenteDto;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.RenteEtendueDto;
import ch.globaz.tmmas.rentesservice.application.service.FeignPersonnesPhysiqueService;
import ch.globaz.tmmas.rentesservice.application.service.RenteService;
import ch.globaz.tmmas.rentesservice.domain.model.Rente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rentes")
public class RentesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentesController.class);

	@Autowired
	RenteService renteService;

	@Autowired
	FeignPersonnesPhysiqueService personnePhysiqueService;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RenteDto> saveRente(@RequestBody RenteDto dto){

		LOGGER.debug("createRente(), {}",dto);

		Rente rente = renteService.sauve(Rente.builder(dto.getNumero(),Long.valueOf(dto.getRequerantId()),dto.getDateEnregistrement()));

		return new ResponseEntity<>(RenteDto.fromEntity(rente), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllRentes(@RequestParam(value = "etendu", required = false)
			                                            String etendu){
		Boolean renteEtendu = Boolean.valueOf(etendu);

		List<Rente> hws = renteService.getAll();


		if(renteEtendu){
			List<RenteEtendueDto> rentesEtendues = getAllAsDto(hws).stream().map(rente -> {

				//recup pp dto
				PersonnesPhysiqueDto ppDto =
						personnePhysiqueService.getPersonnePhysiqueById(rente.getRequerantId());

				return new RenteEtendueDto(rente, ppDto);

			}).collect(Collectors.toList());

			return new ResponseEntity<>(rentesEtendues, HttpStatus.OK);
		}


		return new ResponseEntity<>(getAllAsDto(hws), HttpStatus.OK);
	}

	private List<RenteDto> getAllAsDto (List<Rente> renteList) {
		return renteList.stream().map(RenteDto::fromEntity).collect(Collectors.toList());
	}


}
