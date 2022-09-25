package com.sureservice_backend.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public ClientMapper clientMapper() {
        return new ClientMapper();
    }

    @Bean
    public TechnicianMapper technicianMapper() {return new TechnicianMapper();}

    @Bean
    public SpecialityMapper specialityMapper() {return new SpecialityMapper();}
}