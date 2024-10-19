package com.shecodeafrica.hackathon.NippyResponse.service;

import com.shecodeafrica.hackathon.NippyResponse.domain.request.NewsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.response.ResponseDto;
import com.shecodeafrica.hackathon.NippyResponse.entity.NewsCategoryEntity;
import com.shecodeafrica.hackathon.NippyResponse.entity.NewsEntity;
import com.shecodeafrica.hackathon.NippyResponse.repository.NewsCategoryRepository;
import com.shecodeafrica.hackathon.NippyResponse.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;

    public ResponseEntity<?> createNewsCategory(String name)   {
        ResponseDto response = new ResponseDto();
        try{

            var newsCategories = doesNewsCategoryExist(name);
            if (newsCategories){
                response = new ResponseDto("400", "Category already exists", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            NewsCategoryEntity newsCategoryEntity = new NewsCategoryEntity();
            newsCategoryEntity.setName(name.toLowerCase());

            try{
                newsCategoryRepository.save(newsCategoryEntity);
            }catch (Exception ex){
                response = new ResponseDto("500", "Unable to save news category to database", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response = new ResponseDto("200", "Successful", newsCategoryEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> findNewsCategoryByName(String name)   {
        ResponseDto response = new ResponseDto();
        try{
            var newsCategories = doesNewsCategoryExist(name);
            if (!newsCategories){
                response = new ResponseDto("400", "Category not found", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response = new ResponseDto("200", "Successful", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findNewsCategories()   {
        ResponseDto response = new ResponseDto();
        try{
            var newsCategories = newsCategoryRepository.findAll();
            if (newsCategories.isEmpty()){
                response = new ResponseDto("400", "No category found", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response = new ResponseDto("200", "Successful", newsCategories);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean doesNewsCategoryExist(String name){
        var newsCategories = newsCategoryRepository.findByName(name);
        return newsCategories != null;
    }
}
