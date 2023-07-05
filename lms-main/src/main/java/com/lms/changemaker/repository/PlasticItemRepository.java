package com.lms.changemaker.repository;

import com.lms.changemaker.entity.Module;
import com.lms.changemaker.entity.PlasticCategory;
import com.lms.changemaker.entity.PlasticItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlasticItemRepository  extends JpaRepository<PlasticItem, Integer> {

    @Query("SELECT p FROM PlasticItem p WHERE p.gameId = :gameId order by p.itemId")
    List<PlasticItem> findPlasticItemByGameId(int gameId);
    
}
