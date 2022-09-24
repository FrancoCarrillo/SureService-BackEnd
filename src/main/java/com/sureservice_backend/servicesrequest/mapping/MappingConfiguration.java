package com.sureservice_backend.servicesrequest.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("serviceRequestMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ServiceRequestMapper serviceRequestMapper(){
        return new ServiceRequestMapper();
    }
}
