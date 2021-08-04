package br.com.mgr.personapi.controller.v1;

import br.com.mgr.personapi.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.controller.v1.dto.response.Response;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.service.person.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<Response<List<PersonDto>>> findAll() {
        Response<List<PersonDto>> response = new Response<>();
        response.setData(personService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
