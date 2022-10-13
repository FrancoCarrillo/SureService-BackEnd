package com.sureservice_backend.security.mapping;

import com.sureservice_backend.security.domain.model.entity.User;
import com.sureservice_backend.security.resource.UserResource;
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

    public Page<UserResource> modelListToPage(List<User> modelList, Pageable pageable) {

        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }



}