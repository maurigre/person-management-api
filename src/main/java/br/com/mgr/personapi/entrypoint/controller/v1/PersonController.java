package br.com.mgr.personapi.entrypoint.controller.v1;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usecase.DeletePersonUseCase;
import br.com.mgr.personapi.core.usecase.SearchPersonUseCase;
import br.com.mgr.personapi.core.usecase.UpdatePersonUseCase;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.response.Response;
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
    static final String PATH = VERSION + "/people";

    private final CreatePersonUseCase createPersonUseCase;
    private final SearchPersonUseCase searchPersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<PersonDto>> create(@RequestBody @Valid PersonDto personDto) {
        final Person person = createPersonUseCase.create(PersonDtoMapper.personDtoToPerson(personDto));
        final PersonDto dto = PersonDtoMapper.personToPersonDto(person);
        createSelfLink(dto, dto.getId());
        Response<PersonDto> response = new Response<>();
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<PersonDto>>> findAll() {
        final List<Person> people = searchPersonUseCase.findAll();
        final List<PersonDto> dtos = new ArrayList<>();
        createSelfLink(people, dtos);
        Response<List<PersonDto>> response = new Response<>();
        response.setData(dtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Response<PersonDto>> findById(@PathVariable("id")  String id ) {
        Person person = searchPersonUseCase.findById(UUID.fromString(id));
        return getResponseResponseEntity(person);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id){
        deletePersonUseCase.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Response<PersonDto>> updateById(@PathVariable("id") String id,
                                     @RequestBody @Valid PersonDto personDto){
        Person person = updatePersonUseCase.updateById(
                UUID.fromString(id),
                PersonDtoMapper.personDtoToPerson(personDto)
        );
        return getResponseResponseEntity(person);
    }

    private ResponseEntity<Response<PersonDto>> getResponseResponseEntity(Person person) {
       final PersonDto dto = PersonDtoMapper.personToPersonDto(person);
        createSelfLink(dto, dto.getId());
        Response<PersonDto> response = new Response<>();
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void createSelfLink(PersonDto dto, String id) {
       dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("List Person").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withRel("Person").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).updateById(id, dto)).withRel("Update Person").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).deleteById(id)).withRel("Delete Person").withType("DELETE"));
    }

    private void createSelfLink(List<Person> people, List<PersonDto> dtos) {
        for (Person person : people){
            PersonDto dto = PersonDtoMapper.personToPersonDto(person);
            createSelfLink(dto, person.getId().toString());//TODO rever ID
            dtos.add(dto);
        }
    }
}
