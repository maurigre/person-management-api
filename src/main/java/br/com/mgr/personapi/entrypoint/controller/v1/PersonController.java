package br.com.mgr.personapi.entrypoint.controller.v1;

import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.response.Response;
import br.com.mgr.personapi.service.person.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PersonController.PATH)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    static final String VERSION = "/v1";
    static final String PATH = VERSION + "/peoples";

    private final PersonService personService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<PersonDto>> create(@RequestBody @Valid PersonDto personDto) {
        final PersonEntity personEntity = personService.create(personDto);
        final PersonDto dto = PersonDtoMapper.personEntityToPersonDto(personEntity);
        createSelfLink(dto, personEntity.getId());
        Response<PersonDto> response = new Response<>();
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<PersonDto>>> findAll() {
        final List<PersonEntity> personEntities = personService.findAll();
        final List<PersonDto> dtos = new ArrayList<>();
        createSelfLink(personEntities, dtos);
        Response<List<PersonDto>> response = new Response<>();
        response.setData(dtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Response<PersonDto>> findById(@PathVariable("id")  String id ) {
        final PersonEntity personEntity = personService.findById(UUID.fromString(id));
        final PersonDto dto = PersonDtoMapper.personEntityToPersonDto(personEntity);
        createSelfLink(dto, personEntity.getId());
        Response<PersonDto> response = new Response<>();
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void createSelfLink(PersonDto dto, UUID id) {
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("List Person"));
        dto.add(linkTo(methodOn(PersonController.class).findById(id.toString())).withRel("Person"));
        //dto.add(linkTo(methodOn(PersonController.class).delete(id)).withRel("Delete Person"));
    }

    private void createSelfLink(List<PersonEntity> personEntities, List<PersonDto> dtos) {
        for (PersonEntity person : personEntities){
            PersonDto dto = PersonDtoMapper.personEntityToPersonDto(person);
            createSelfLink(dto, person.getId());
            dtos.add(dto);
        }
    }
}
