package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.mapping.dto.TechnicianEntityMapper;
import com.sureservice_backend.security.resource.TechnicianResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TechnicianMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };

    // Object Mapping
    public TechnicianResource toResource(Technician model) {

        mapper.addConverter(roleToString);
        return mapper.map(new TechnicianEntityMapper(model.getId(), model.getUsername(), model.getEmail(),
                (List)model.getRol(), model.getProfessional_profile(), model.getValoration(), model.getDistrict(),
                model.getDisponibility(), model.getSpeciality()), TechnicianResource.class);

    }

    public Page<TechnicianResource> modelListToPage(List<Technician> modelList, Pageable pageable) {

        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, TechnicianResource.class), pageable, modelList.size());
    }
}
