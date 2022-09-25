package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.Speciality;
import com.sureservice_backend.security.domain.service.communication.SpecialityRequest;
import com.sureservice_backend.security.resource.SpecialityResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class SpecialityMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public SpecialityResource toResource(Speciality model){
        return mapper.map(model,SpecialityResource.class);
    }


    public List<SpecialityResource> modelListToResource(List<Speciality> modelList){return mapper.mapList(modelList, SpecialityResource.class); }

    public Speciality toModel(SpecialityRequest resource){
        return mapper.map(resource,Speciality.class);
    }

}
