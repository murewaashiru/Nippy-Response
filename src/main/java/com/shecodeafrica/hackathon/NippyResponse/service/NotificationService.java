package com.shecodeafrica.hackathon.NippyResponse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shecodeafrica.hackathon.NippyResponse.domain.request.SmsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final ObjectMapper objectMapper;

    public ResponseEntity<?> sendSMS(SmsRequestDto request)   {
        ResponseDto response = new ResponseDto();
        try{
            response = new ResponseDto("200", "Successful", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
