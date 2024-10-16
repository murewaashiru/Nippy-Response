package com.shecodeafrica.hackathon.NippyResponse.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.shecodeafrica.hackathon.NippyResponse.domain.response.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDto> badRequestException(Exception e, HttpServletRequest request) {
        log.info("BadRequestException: {}",e.getMessage());
        ResponseDto response = new ResponseDto();
        return ResponseEntity.status(422).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("onMethodArgumentNotValidException: {}",e.getMessage());
        ArrayList<String> errorList= new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            //errorList.add(new ResponseDto.Errors(error.getField(), error.getDefaultMessage()));
            errorList.add(error.getField()+ ": " +error.getDefaultMessage());
        });

        ResponseDto response = new ResponseDto("400", "1 or more validation errors occurred", errorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception e, HttpServletRequest request) {
        log.info("Exception: {}",e.getMessage());
        ResponseDto response = new ResponseDto("500", e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ResponseDto> handleJsonMappingException(Exception e, HttpServletRequest request) {
        log.info("JsonMappingException: {}",e.getMessage());
        ResponseDto response = new ResponseDto("500", "Json Mapping Exception occurred", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto> handleRuntimeException(Exception e, HttpServletRequest request) {
        log.info("RuntimeException: {}",e.getMessage());
        ResponseDto response = new ResponseDto("500", e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
