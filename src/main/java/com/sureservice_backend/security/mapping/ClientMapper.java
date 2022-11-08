package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.mapping.dto.ClientEntityMapper;
import com.sureservice_backend.security.resource.ClientResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ClientMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ClientResource toResource(Client model) {
        ClientEntityMapper client = new ClientEntityMapper(model.getId(), model.getImage_Id(), model.getUsername(), model.getEmail(),
                model.getRol(), model.getAmount_reservation(), model.getName(), model.getLast_name(), model.getTelephone_number(), model.getDni(), model.getImage_url());
        return mapper.map(client, ClientResource.class);
    }




}
