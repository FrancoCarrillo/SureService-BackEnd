package com.sureservice_backend.servicesrequest.mapping;

import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;
import com.sureservice_backend.servicesrequest.resource.CreateServiceRequestResource;
import com.sureservice_backend.servicesrequest.resource.ServiceRequestResource;
import com.sureservice_backend.servicesrequest.resource.UpdateServiceRequestResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ServiceRequestMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping
    public ServiceRequestResource toResource(ServiceRequest model){
        return mapper.map(model,ServiceRequestResource.class);
    }

    public Page<ServiceRequestResource> modelListPage(List<ServiceRequest> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ServiceRequestResource.class),pageable,modelList.size());
    }

    public List<ServiceRequestResource> modelListToResource(List<ServiceRequest> modelList){return mapper.mapList(modelList, ServiceRequestResource.class); }

    public ServiceRequest toModel(CreateServiceRequestResource resource){
        return mapper.map(resource,ServiceRequest.class);
    }

    public ServiceRequest toModel(UpdateServiceRequestResource resource){
        return mapper.map(resource,ServiceRequest.class);
    }
}
