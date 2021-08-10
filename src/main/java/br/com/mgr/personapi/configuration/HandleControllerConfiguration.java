package br.com.mgr.personapi.configuration;

import br.com.mgr.personapi.entrypoint.controller.v1.ControllerApiAdviceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandleControllerConfiguration {

    @Bean
    public ControllerApiAdviceHandler handleControllerConfigration() {
        return new ControllerApiAdviceHandler();

    }


}

