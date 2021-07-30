package br.com.mgr.personapi.configuration;

import br.com.mgr.aluno.usecase.RegistroAluno;
import br.com.mgr.controller.AlunoController;
import br.com.mgr.converter.AlunoRequestConverter;
import br.com.mgr.personapi.controller.v1.PersonController;
import br.com.mgr.personapi.core.usercase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usercase.imp.CreatePersonUserCaseImp;
import br.com.mgr.personapi.dataprovider.repository.PersonDao;
import br.com.mgr.personapi.dataprovider.repository.PersonDaoImp;
import br.com.mgr.personapi.service.person.PersonService;
import br.com.mgr.personapi.service.person.imp.PersonServiceImp;
import br.com.mgr.repository.AlunoRepositoryImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mauri Reis
 * @since 29/07/21
 */

@Configuration
public class PersonControllerConfiguration {

    @Bean
    public PersonController personControllerConfigration(
            RegistroAluno registroAluno,
            AlunoRequestConverter requestConverter) {
        return new AlunoController(registroAluno, requestConverter);

    }

    @Bean
    public PersonService createPersonConfigration() {
        new PersonServiceImp(new PersonDaoImp())
        return new CreatePersonUserCaseImp(new PersonDaoImp());

    }

    @Bean
    public AlunoRequestConverter converterPersonConfigration() {
        return new AlunoRequestConverter();

    }


}

