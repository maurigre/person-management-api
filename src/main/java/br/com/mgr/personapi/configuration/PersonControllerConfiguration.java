package br.com.mgr.personapi.configuration;

import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usecase.DeletePersonUseCase;
import br.com.mgr.personapi.core.usecase.SearchPersonUseCase;
import br.com.mgr.personapi.core.usecase.UpdatePersonUseCase;
import br.com.mgr.personapi.dataprovider.repository.PersonJpaRepositoryImp;
import br.com.mgr.personapi.entrypoint.controller.v1.PersonController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mauri Reis
 * @since 29/07/21
 */

@Configuration
public class PersonControllerConfiguration {

    @Bean
    public PersonController personControllerConfig(
            CreatePersonUseCase createPersonUseCase,
            SearchPersonUseCase searchPersonUseCase,
            DeletePersonUseCase deletePersonUseCase,
            UpdatePersonUseCase updatePersonUseCase) {
        return new PersonController(createPersonUseCase, searchPersonUseCase, deletePersonUseCase, updatePersonUseCase);
    }

    @Bean
    public CreatePersonUseCase createPersonUseCaseConfiguration(PersonJpaRepositoryImp personJpaRepositoryImp){
        return  new CreatePersonUseCase(personJpaRepositoryImp);
    }

    @Bean
    public SearchPersonUseCase searchPersonUseCaseConfiguration(PersonJpaRepositoryImp personJpaRepositoryImp){
        return  new SearchPersonUseCase(personJpaRepositoryImp);
    }

    @Bean
    public DeletePersonUseCase deletePersonUseCaseConfiguration(PersonJpaRepositoryImp personJpaRepositoryImp){
        return  new DeletePersonUseCase(personJpaRepositoryImp);
    }

    @Bean
    public UpdatePersonUseCase updatePersonUseCaseConfiguration(PersonJpaRepositoryImp personJpaRepositoryImp){
        return  new UpdatePersonUseCase(personJpaRepositoryImp);
    }


}

