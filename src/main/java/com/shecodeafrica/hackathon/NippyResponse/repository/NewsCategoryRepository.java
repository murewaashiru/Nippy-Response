package com.shecodeafrica.hackathon.NippyResponse.repository;

import com.shecodeafrica.hackathon.NippyResponse.entity.NewsCategoryEntity;
import com.shecodeafrica.hackathon.NippyResponse.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCategoryRepository extends JpaRepository<NewsCategoryEntity, Long> {
    @Query(value = "SELECT * FROM NEWS_CATEGORY WHERE NAME=?",nativeQuery = true)
    NewsCategoryEntity findByName(String name);
}