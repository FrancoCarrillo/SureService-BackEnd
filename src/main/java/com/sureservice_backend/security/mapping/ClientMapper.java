package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.domain.model.entity.User;
import com.sureservice_backend.security.mapping.dto.ClientEntityMapper;
import com.sureservice_backend.security.resource.ClientResource;
import com.sureservice_backend.security.resource.RoleResource;
import com.sureservice_backend.security.resource.UserResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClientMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };

    // Object Mapping
    public ClientResource toResource(Client model) {

        mapper.addConverter(roleToString);
        return mapper.map(new ClientEntityMapper(model.getId(), model.getUsername(), model.getEmail(), (List)model.getRoles(), model.getAmount_reservation()), ClientResource.class);

    }

    public Page<ClientResource> modelListToPage(List<Client> modelList, Pageable pageable) {

        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, ClientResource.class), pageable, modelList.size());
    }

}
