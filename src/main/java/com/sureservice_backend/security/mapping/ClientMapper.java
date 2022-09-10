package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.mapping.dto.ClientEntityMapper;
import com.sureservice_backend.security.resource.ClientResource;
import com.sureservice_backend.security.resource.RoleResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.logging.Logger;

public class ClientMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ClientResource toResource(Client model) {


        ClientEntityMapper client = new ClientEntityMapper(model.getId(), model.getUsername(), model.getEmail(),
                model.getRol(), model.getAmount_reservation());
        return mapper.map(client, ClientResource.class);
    }




}
