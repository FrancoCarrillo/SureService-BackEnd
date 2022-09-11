package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.mapping.dto.TechnicianEntityMapper;
import com.sureservice_backend.security.resource.TechnicianResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;

public class TechnicianMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public TechnicianResource toResource(Technician model) {
        TechnicianEntityMapper technician = new TechnicianEntityMapper(model.getId(), model.getUsername(), model.getEmail(),
                model.getRol(), model.getProfessional_profile(), model.getValoration(), model.getDistrict(), model.getDisponibility(), model.getSpeciality() );


        return mapper.map(technician, TechnicianResource.class);
    }
}
