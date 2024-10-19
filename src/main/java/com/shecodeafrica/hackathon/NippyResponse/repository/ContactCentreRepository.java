package com.shecodeafrica.hackathon.NippyResponse.repository;

import com.shecodeafrica.hackathon.NippyResponse.entity.ContactCentreEntity;
import com.shecodeafrica.hackathon.NippyResponse.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactCentreRepository extends JpaRepository<ContactCentreEntity, Long> {
    @Query(value = "SELECT * FROM CONTACT_CENTRE WHERE CATEGORY=?",nativeQuery = true)
    List<ContactCentreEntity> findByCategory(String category);

    ContactCentreEntity findByName(String name);

}
