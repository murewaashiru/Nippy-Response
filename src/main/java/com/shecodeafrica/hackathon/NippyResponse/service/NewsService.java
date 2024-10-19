package com.shecodeafrica.hackathon.NippyResponse.service;

import com.shecodeafrica.hackathon.NippyResponse.domain.request.NewsRequestDto;
import com.shecodeafrica.hackathon.NippyResponse.domain.response.ResponseDto;
import com.shecodeafrica.hackathon.NippyResponse.entity.NewsCategoryEntity;
import com.shecodeafrica.hackathon.NippyResponse.entity.NewsEntity;
import com.shecodeafrica.hackathon.NippyResponse.exception.BadRequestException;
import com.shecodeafrica.hackathon.NippyResponse.repository.NewsCategoryRepository;
import com.shecodeafrica.hackathon.NippyResponse.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsCategoryRepository newsCategoryRepository;

    public ResponseEntity<?> createNews(NewsRequestDto request)   {
        ResponseDto response = new ResponseDto();
        try{

            var newsCategories = newsCategoryRepository.findByName(request.getCategory());
            if (newsCategories == null){
                response = new ResponseDto("400", "Invalid category", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            NewsEntity newsEntity = new NewsEntity();
            newsEntity.setCategory(request.getCategory());
            newsEntity.setMessage(request.getMessage());

            try{
                newsRepository.save(newsEntity);
            }catch (Exception ex){
                response = new ResponseDto("500", "Unable to save news to database", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response = new ResponseDto("200", "Successful", newsEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            log.error("Exception at createNews(): {}",ex.getMessage());
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> findByCategory(String category)   {
        ResponseDto response = new ResponseDto();
        try{
            List<NewsEntity> newsEntity = newsRepository.findByCategory(category);

            if (newsEntity.isEmpty()){
                response = new ResponseDto("400", "No news for this category", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response = new ResponseDto("200", "Successful", newsEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findAllNews()   {
        ResponseDto response = new ResponseDto();
        try{
            var newsCategories = newsRepository.findAll();
            if (newsCategories.isEmpty()){
                response = new ResponseDto("400", "No news found", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response = new ResponseDto("200", "Successful", newsCategories);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseDto("500", ex.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
