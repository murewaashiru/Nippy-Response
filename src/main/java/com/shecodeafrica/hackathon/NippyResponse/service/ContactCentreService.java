package com.shecodeafrica.hackathon.NippyResponse.service;

import com.shecodeafrica.hackathon.NippyResponse.domain.response.ContactCentresResponseDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.response.ResponseDto;
import com.shecodeafrica.hackathon.NippyResponse.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactCentreService {
    public ResponseEntity<?> getContactCentreInformation(String type)   {
        ResponseDto response = new ResponseDto();
        try{
            String[] types= {"police", "fire", "dss", "efcc"};
            return switch (type) {
                case "police" -> getPoliceInformation(type);
                case "fire" -> null;
                default -> throw new BadRequestException("Accepted types are: " + Arrays.toString(types));
            };
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<?> getPoliceInformation(String type)   {
        ResponseDto response = new ResponseDto();
        ContactCentresResponseDto contactCentresResponse = new ContactCentresResponseDto();
        try{
            String[] phoneNumber = {"09000000000", "banana", "orange"};
            contactCentresResponse = new ContactCentresResponseDto("VI Police", "VI, Lagos", List.of(phoneNumber), "Lagos Island", "Lagos");

            response = new ResponseDto("200", "Successful", contactCentresResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
