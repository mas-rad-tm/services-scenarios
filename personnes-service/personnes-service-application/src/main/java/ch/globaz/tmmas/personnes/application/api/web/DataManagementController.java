package ch.globaz.tmmas.personnes.application.api.web;

import ch.globaz.tmmas.personnes.application.api.dto.PersonnesPhysiqueDto;
import ch.globaz.tmmas.personnes.application.api.dto.datamanagement.SampleDataDto;
import ch.globaz.tmmas.personnes.application.service.PersonnePhysiqueService;
import ch.globaz.tmmas.personnes.domain.model.NSS;
import ch.globaz.tmmas.personnes.domain.model.PersonnePhysique;
import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/data")
public class DataManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataManagementController.class);

    @Autowired
    PersonnePhysiqueService personneService;




    @RequestMapping(value = "/sample",method = RequestMethod.POST)
    public ResponseEntity<String> createPerson(@RequestBody SampleDataDto dto){
        LOGGER.debug("createPerson(), {}",dto);

        personneService.genereteRandomAccessDatas(Integer.valueOf(dto.getNbValeurs()));

        LOGGER.debug("sample personns create, {} elements inserted",dto.getNbValeurs());

        return new ResponseEntity<String>("OK, " + dto.getNbValeurs() + " Created",HttpStatus.CREATED);
    }


}
