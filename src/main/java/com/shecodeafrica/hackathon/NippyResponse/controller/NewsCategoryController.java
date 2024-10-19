package com.shecodeafrica.hackathon.NippyResponse.controller;

import com.shecodeafrica.hackathon.NippyResponse.domain.request.NewsCategoryRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.request.NewsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.service.NewsCategoryService;
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
@RequestMapping("/news-category")
public class NewsCategoryController {
    private final NewsCategoryService newsCategoryService;

    @Operation(description = "This is to create a new news category")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsCategoryRequestDto request, HttpServletRequest httpServletRequest) throws ServerErrorException {
        return newsCategoryService.createNewsCategory(request.getName());
    }

    @Operation(description = "This is to retrieve new categories")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findNewsCategories(@RequestParam Optional<String> name) throws Exception {
        if (name.isPresent()){
            return newsCategoryService.findNewsCategoryByName(name.get().toLowerCase());
        }
        return newsCategoryService.findNewsCategories();
    }
}
