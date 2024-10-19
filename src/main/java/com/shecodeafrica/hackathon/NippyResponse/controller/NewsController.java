package com.shecodeafrica.hackathon.NippyResponse.controller;

import com.shecodeafrica.hackathon.NippyResponse.domain.request.NewsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.request.SmsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerErrorException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @Operation(description = "This is to create a new alert")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsRequestDto request, HttpServletRequest httpServletRequest) throws ServerErrorException {
        return newsService.createNews(request);
    }

    @Operation(description = "This is to retrieve all news alerts")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findNews(@RequestParam Optional<String> category) throws Exception {
        if (category.isPresent()){
            return newsService.findByCategory(category.get().toLowerCase());
        }
        return newsService.findAllNews();
    }


}
