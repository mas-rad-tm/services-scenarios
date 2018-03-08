package ch.globaz.tmmas.personnes.application.api.web;

import ch.globaz.tmmas.personnes.application.api.dto.PersonnesPhysiqueDto;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config-test")
public class ConfigControllerTest {

	@Value("${config-test-label}")
	private String configTestLabel;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> testRefresh(){


		return new ResponseEntity<String>(configTestLabel,HttpStatus.OK);
	}
}
