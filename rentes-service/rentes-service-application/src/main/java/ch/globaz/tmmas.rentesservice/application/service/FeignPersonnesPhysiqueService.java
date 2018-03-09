package ch.globaz.tmmas.rentesservice.application.service;

import ch.globaz.tmmas.rentesservice.application.api.dto.PersonnesPhysiqueDto;
import ch.globaz.tmmas.rentesservice.domain.model.PersonnePhysique;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(decode404 = true,value = "personnes-service")
public interface FeignPersonnesPhysiqueService {

	@LoadBalanced
	@RequestMapping(method = RequestMethod.GET, value = "/personnes-service/personnes/{personnesId}")
	ResponseEntity<PersonnesPhysiqueDto> getPersonnePhysiqueById(@PathVariable("personnesId") Long tiersId);

	@RequestMapping(method = RequestMethod.GET, value = "/personnes-service/personnes?countOnly=true")
	ResponseEntity<List<PersonnePhysique>> countPersonnesPhysique();

	@LoadBalanced
	@RequestMapping(method = RequestMethod.GET, value = "/personnes-service/personnes")
	ResponseEntity<List<PersonnesPhysiqueDto>> getPersonnesPhysique();


}