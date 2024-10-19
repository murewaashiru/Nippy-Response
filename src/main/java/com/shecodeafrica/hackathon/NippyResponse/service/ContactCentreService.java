package com.shecodeafrica.hackathon.NippyResponse.service;

import com.shecodeafrica.hackathon.NippyResponse.domain.request.ContactCentreRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.response.ContactCentresResponseDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.response.ResponseDto;
import com.shecodeafrica.hackathon.NippyResponse.entity.ContactCentreEntity;
import com.shecodeafrica.hackathon.NippyResponse.entity.NewsCategoryEntity;
import com.shecodeafrica.hackathon.NippyResponse.exception.BadRequestException;
import com.shecodeafrica.hackathon.NippyResponse.repository.ContactCentreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactCentreService {
    private final ContactCentreRepository contactCentreRepository;

    public ResponseEntity<?> createContactCentre(ContactCentreRequestDto request) {
        ResponseDto response = new ResponseDto();
        try {
            ContactCentreEntity centreExist = contactCentreRepository.findByName(request.getName());

            if (centreExist != null) {
                response = new ResponseDto("400", "Centre already exists", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            ContactCentreEntity contactCentreEntity = new ContactCentreEntity();
            contactCentreEntity.setName(request.getName());
            contactCentreEntity.setCategory(request.getCategory().toLowerCase());
            contactCentreEntity.setAddress(request.getAddress());
            contactCentreEntity.setPhoneNumber(request.phoneNumber);
            contactCentreEntity.setLga(request.getLga());
            contactCentreEntity.setState(request.getState());

            try {
                contactCentreRepository.save(contactCentreEntity);
            } catch (Exception ex) {
                response = new ResponseDto("500", "Unable to save contact centre to database", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response = new ResponseDto("200", "Successful", contactCentreEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findContactCentreInformation(String category)   {
        ResponseDto response = new ResponseDto();
        try{
            String[] categories= {"police", "fire", "dss", "efcc", "weather"};

            boolean isValidCategory = Arrays.asList(categories).contains(category);
            if (!isValidCategory){
                throw new BadRequestException("Accepted types are: " + Arrays.toString(categories));
            }
//            return switch (category) {
//                case "police" -> getPoliceInformation(category);
//                case "fire" -> null;
//                default -> throw new BadRequestException("Accepted types are: " + Arrays.toString(types));
//            };

            List<ContactCentreEntity> centres = contactCentreRepository.findAll().stream().filter(a -> Objects.equals(a.getCategory(), category)).toList();
            if (centres.isEmpty()){
                response = new ResponseDto("400", "No information found", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response = new ResponseDto("200", "Successful", centres);
            return new ResponseEntity<>(response, HttpStatus.OK);
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

    public ResponseEntity<?> findAllContactCentres()   {
        ResponseDto response = new ResponseDto();
        try{
            var contactCentres = contactCentreRepository.findAll();
            if (contactCentres.isEmpty()){
                response = new ResponseDto("400", "No information found", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response = new ResponseDto("200", "Successful", contactCentres);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean doesContactCentreCategoryExist(String category){
        var contactCentreCategories = contactCentreRepository.findByCategory(category);
        return contactCentreCategories != null;
    }
}
