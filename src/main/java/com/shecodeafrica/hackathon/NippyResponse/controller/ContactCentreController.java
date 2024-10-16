package com.shecodeafrica.hackathon.NippyResponse.controller;

import com.shecodeafrica.hackathon.NippyResponse.service.ContactCentreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact-centre")
public class ContactCentreController {
    private final ContactCentreService contactCentreService;
    @Operation(description = "This is to retrieve information about contact centres")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContactCentreInformation(@RequestParam String type) throws Exception {
        return contactCentreService.getContactCentreInformation(type);
    }
}
