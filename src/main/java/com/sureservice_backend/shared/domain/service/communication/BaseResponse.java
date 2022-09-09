package com.sureservice_backend.shared.domain.service.communication;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public abstract class BaseResponse<T> {
    private boolean success;
    private String message;
    private T resource;

    public BaseResponse(String message) {
        this.success = false;
        this.message = message;
        this.resource = null;
    }

    public BaseResponse(T resource) {
        this.success = true;
        this.resource = resource;
        this.message = Strings.EMPTY;
    }
}