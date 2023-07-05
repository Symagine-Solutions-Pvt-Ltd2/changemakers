package com.lms.changemaker.serviceimpl;

import com.lms.changemaker.entity.PlasticCategory;
import com.lms.changemaker.repository.PlasticCategoryRepository;
import com.lms.changemaker.service.PlasticDragDropCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PlasticCategoryServiceImpl implements PlasticDragDropCategoryService {

    @Autowired
    PlasticCategoryRepository plasticCategoryRepository;

    @Override
    @Transactional
    public PlasticCategory addPlasticDragDrop(PlasticCategory plasticCategory) {
        return plasticCategoryRepository.save(plasticCategory);
    }

    @Override
    public List<PlasticCategory> findAllPlasticDragDropCategory() {
        return plasticCategoryRepository.findAll();
    }

    @Override
    public PlasticCategory findPlasticDragDropCategoryById(int catId) {
        return plasticCategoryRepository.findById(catId).get();
    }

    @Override
    public List<PlasticCategory> findPlasticCategoriesByGameId(int gameId) {
        return plasticCategoryRepository.findPlasticCategoriesByGameId(gameId);
    }
}
