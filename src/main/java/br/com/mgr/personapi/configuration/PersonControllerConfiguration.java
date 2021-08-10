package br.com.mgr.personapi.configuration;

import br.com.mgr.personapi.core.usecase.DeletePersonUseCase;
import br.com.mgr.personapi.core.usecase.UpdatePersonUseCase;
import br.com.mgr.personapi.core.usecase.imp.DeletePersonUseCaseImp;
import br.com.mgr.personapi.core.usecase.imp.UpdatePersonUseCaseImp;
import br.com.mgr.personapi.entrypoint.controller.v1.PersonController;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usecase.SearchPersonUseCase;
import br.com.mgr.personapi.core.usecase.imp.CreatePersonUseCaseImp;
import br.com.mgr.personapi.core.usecase.imp.SearchPersonUseCaseImp;
import br.com.mgr.personapi.dataprovider.repository.PersonDao;
import br.com.mgr.personapi.dataprovider.repository.PersonDaoImp;
import br.com.mgr.personapi.service.person.PersonService;
import br.com.mgr.personapi.service.person.imp.PersonServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mauri Reis
 * @since 29/07/21
 */

@Configuration
public class PersonControllerConfiguration {

    @Autowired
    private PersonDao personDao;

    @Bean
    public PersonController personControllerConfigration(PersonService service) {
        return new PersonController(service);

    }

    @Bean
    public PersonDaoImp repositoryImpConfigration() {
        return new PersonDaoImp(personDao);
    }

    @Bean
    public PersonService personServiceConfigration(
            CreatePersonUseCase createPersonUseCase,
            SearchPersonUseCase searchPersonUseCase,
            DeletePersonUseCase deletePersonUseCase,
            UpdatePersonUseCase updatePersonUseCase) {
        return new PersonServiceImp( createPersonUseCase, searchPersonUseCase, deletePersonUseCase, updatePersonUseCase);
    }

    @Bean
    public CreatePersonUseCase createPersonUseCaseConfiguration(PersonDaoImp personDaoImp){
        return  new CreatePersonUseCaseImp(personDaoImp);
    }

    @Bean
    public SearchPersonUseCase searchPersonUseCaseConfiguration(PersonDaoImp personDaoImp){
        return  new SearchPersonUseCaseImp(personDaoImp);
    }

    @Bean
    public DeletePersonUseCase deletePersonUseCaseConfiguration(PersonDaoImp personDaoImp){
        return  new DeletePersonUseCaseImp(personDaoImp);
    }

    @Bean
    public UpdatePersonUseCase updatePersonUseCaseConfiguration(PersonDaoImp personDaoImp){
        return  new UpdatePersonUseCaseImp(personDaoImp);
    }

}

