package com.shecodeafrica.hackathon.NippyResponse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServerErrorException extends Exception{

    private static final long serialVersionUID = 3561212273786671953L;

    public ServerErrorException(String message) {
        super(message);
    }
}
