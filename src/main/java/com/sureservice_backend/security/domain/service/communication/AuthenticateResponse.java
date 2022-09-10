package com.sureservice_backend.security.domain.service.communication;

import com.sureservice_backend.security.resource.AuthenticateResource;
import com.sureservice_backend.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}
