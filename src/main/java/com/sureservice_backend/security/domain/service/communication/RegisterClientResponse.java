package com.sureservice_backend.security.domain.service.communication;

import com.sureservice_backend.security.resource.ClientResource;
import com.sureservice_backend.shared.domain.service.communication.BaseResponse;

public class RegisterClientResponse extends BaseResponse<ClientResource> {
    public RegisterClientResponse(String message) {
        super(message);
    }

    public RegisterClientResponse(ClientResource resource) {
        super(resource);
    }
}
