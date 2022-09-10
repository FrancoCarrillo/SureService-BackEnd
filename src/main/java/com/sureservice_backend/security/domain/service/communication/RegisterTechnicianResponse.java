package com.sureservice_backend.security.domain.service.communication;

import com.sureservice_backend.security.resource.TechnicianResource;
import com.sureservice_backend.shared.domain.service.communication.BaseResponse;

public class RegisterTechnicianResponse extends BaseResponse<TechnicianResource> {

    public RegisterTechnicianResponse(String message) {
        super(message);
    }

    public RegisterTechnicianResponse(TechnicianResource resource) {
        super(resource);
    }
}
