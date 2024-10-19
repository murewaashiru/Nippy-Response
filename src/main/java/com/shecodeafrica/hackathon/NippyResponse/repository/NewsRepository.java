package com.shecodeafrica.hackathon.NippyResponse.repository;

import com.shecodeafrica.hackathon.NippyResponse.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    @Query(value = "SELECT * FROM NEWS WHERE CATEGORY=?",nativeQuery = true)
    List<NewsEntity> findByCategory(String category);

}
