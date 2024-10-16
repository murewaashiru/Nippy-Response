package com.shecodeafrica.hackathon.NippyResponse.controller;

import com.shecodeafrica.hackathon.NippyResponse.domain.request.SmsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping(value = "/send-sms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendSMS(@Valid @RequestBody SmsRequestDto request, HttpServletRequest httpServletRequest) throws ServerErrorException {
        return notificationService.sendSMS(request);
    }
}
