package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.Speciality;
import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;
import com.sureservice_backend.security.domain.service.communication.SpecialityRequest;
import com.sureservice_backend.security.domain.service.communication.UpdateTechnicianRequest;
import com.sureservice_backend.security.mapping.dto.TechnicianEntityMapper;
import com.sureservice_backend.security.resource.TechnicianResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;

public class TechnicianMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public TechnicianResource toResource(Technician model) {
        TechnicianEntityMapper technician = new TechnicianEntityMapper(model.getId(), model.getImage_Id(), model.getUsername(), model.getEmail(),
                model.getRol(), model.getProfessional_profile(), model.getValoration(), model.getDistrict(), model.getDisponibility(), model.getSpeciality(),
                model.getName(), model.getLast_name(), model.getDni(), model.getEmail(), model.getImage_url());


        return mapper.map(technician, TechnicianResource.class);
    }

    public Technician toModel(UpdateTechnicianRequest resource){

        return mapper.map(resource,Technician.class);
    }
}
