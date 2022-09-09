package com.sureservice_backend.security.domain.service.communication;

import com.sureservice_backend.security.resource.UserResource;
import com.sureservice_backend.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}