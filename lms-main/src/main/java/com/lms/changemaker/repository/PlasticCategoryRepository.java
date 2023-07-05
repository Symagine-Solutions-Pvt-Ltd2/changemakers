package com.lms.changemaker.repository;

import com.lms.changemaker.entity.Module;
import com.lms.changemaker.entity.PlasticCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlasticCategoryRepository extends JpaRepository<PlasticCategory, Integer>  {

    @Query("SELECT u FROM PlasticCategory u WHERE u.gameId = :gameId")
    List<PlasticCategory> findPlasticCategoriesByGameId(int gameId);
}
