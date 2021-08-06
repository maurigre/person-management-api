package br.com.mgr.personapi.configuration;

import br.com.mgr.personapi.controller.v1.ControllerApiAdviceHandler;
import br.com.mgr.personapi.controller.v1.PersonController;
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

@Configuration
public class HandleControllerConfiguration {

    @Bean
    public ControllerApiAdviceHandler handleControllerConfigration() {
        return new ControllerApiAdviceHandler();

    }


}

