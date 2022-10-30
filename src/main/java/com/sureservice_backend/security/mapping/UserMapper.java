package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.User;
import com.sureservice_backend.security.resource.UserResource;
import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;
import com.sureservice_backend.servicesrequest.resource.ServiceRequestResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User model) {

        return mapper.map(model, UserResource.class);

    }

    public List<User> modelListToResource(List<User> modelList){return mapper.mapList(modelList, User.class); }



}