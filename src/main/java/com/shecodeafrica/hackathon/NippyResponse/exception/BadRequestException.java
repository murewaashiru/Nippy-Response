package com.shecodeafrica.hackathon.NippyResponse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{
    private static final long serialVersionUID = 35612273786671953L;


    public BadRequestException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

}
