package com.shecodeafrica.hackathon.NippyResponse.controller;

import com.shecodeafrica.hackathon.NippyResponse.domain.request.ContactCentreRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.request.NewsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.service.ContactCentreService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerErrorException;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact-centre")
public class ContactCentreController {
    private final ContactCentreService contactCentreService;

    @Operation(description = "This is to create a new Contact Centre")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNews(@Valid @RequestBody ContactCentreRequestDto request, HttpServletRequest httpServletRequest) throws ServerErrorException {
        return contactCentreService.createContactCentre(request);
    }
    @Operation(description = "This is to retrieve information about contact centres")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContactCentreInformation(@RequestParam Optional<String> category) throws Exception {
        if (category.isPresent()){
            return contactCentreService.findContactCentreInformation(category.get().toLowerCase());
        }
        return contactCentreService.findAllContactCentres();
    }

}
