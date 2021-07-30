package br.com.mgr.personapi.controller.v1;

import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import br.com.mgr.personapi.service.person.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getPeoples(){
        PersonEntity person = PersonEntity.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Mauri")
                .lastName("Reis")
                .cpf("11111111111")
                .birthDate(LocalDate.of(2019, 12, 01))
                .phoneEntities(List.of(new PhoneEntity(1L, PhoneType.COMMERCIAL, "16999994444")))
                .build();
        PersonEntity person1 = personService.create(person);
        return person1.toString();
    }
}
