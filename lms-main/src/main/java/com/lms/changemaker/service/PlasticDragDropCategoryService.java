package com.lms.changemaker.service;

import com.lms.changemaker.entity.PlasticCategory;

import java.util.List;

public interface PlasticDragDropCategoryService {

    PlasticCategory addPlasticDragDrop(PlasticCategory plasticCategory);

    List<PlasticCategory> findAllPlasticDragDropCategory();

    PlasticCategory findPlasticDragDropCategoryById(int moduleId);

    List<PlasticCategory> findPlasticCategoriesByGameId(int gameId);
}
