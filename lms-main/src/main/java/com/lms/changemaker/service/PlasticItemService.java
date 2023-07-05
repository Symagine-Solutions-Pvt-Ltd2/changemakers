package com.lms.changemaker.service;

import com.lms.changemaker.entity.PlasticItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlasticItemService {

    PlasticItem addPlasticItem(PlasticItem plasticItem);

    List<PlasticItem> findPlasticItem();

    PlasticItem findPlasticItemById(int itemId);

    String savePlasticItemForDragDropGame(PlasticItem plasticItem, MultipartFile multipartPlasticImage);

    String editPlasticItemForDragDropGame(PlasticItem plasticItem, MultipartFile multipartPlasticImage);


    String getAllPlasticItemByGame(int gameId);

    List<PlasticItem> findPlasticItemByGameId(int gameId);

}
