package ch.globaz.tmmas.zuulapigateway.application.service;

import ch.globaz.tmmas.zuulapigateway.application.dto.RenteDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("rentes-service")
public interface RenteService {

    @RequestMapping(method = RequestMethod.POST, value = "/rentes-service/rentes")
    ResponseEntity<RenteDto> sauveRente(@RequestBody RenteDto dto);
}


