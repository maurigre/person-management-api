package br.com.mgr.personapi.controller.v1;

import br.com.mgr.personapi.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.controller.v1.dto.response.Response;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.service.person.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/peoples")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Response<PersonDto>> create(@RequestBody @Valid PersonDto personDto){

        PersonEntity personEntity = PersonDtoMapper.personDtoToPersonEntity(personDto);
        PersonDto dto = personService.create(personEntity);

        Response<PersonDto> response = new Response<>();
        response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
