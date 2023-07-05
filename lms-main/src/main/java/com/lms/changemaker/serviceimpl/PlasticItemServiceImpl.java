package com.lms.changemaker.serviceimpl;

import com.lms.changemaker.repository.PlasticItemRepository;
import com.lms.changemaker.entity.PlasticItem;
import com.lms.changemaker.service.PlasticItemService;
import com.lms.changemaker.service.S3BucketStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlasticItemServiceImpl implements PlasticItemService {

    @Autowired
    PlasticItemRepository plasticItemRepository;

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    private S3BucketStorageService s3BucketStorageService;

  


    @Override
    @Transactional
    public PlasticItem addPlasticItem(PlasticItem plasticItem) {
        return plasticItemRepository.save(plasticItem);
    }

    @Override
    public List<PlasticItem> findPlasticItem() {
        return plasticItemRepository.findAll();
    }

    @Override
    public PlasticItem findPlasticItemById(int itemId) {
        return plasticItemRepository.findById(itemId).get();
    }

    @Override
    @Transactional
    public String savePlasticItemForDragDropGame(PlasticItem plasticItem, MultipartFile multipartPlasticImage) {
            String msg="";
        try {

            System.out.println(plasticItem.toString());
            String moduleImagePath= s3BucketStorageService.uploadFormImage(multipartPlasticImage);
            plasticItem.setImage(moduleImagePath);
            plasticItem.setCategoryId(plasticItem.getPlasticCategory().getCatId());

                if(addPlasticItem(plasticItem)!=null)
                    msg="Plastic Item for drag and drop game has been added successfully.";

        }   catch (Exception ex){
               ex.printStackTrace();
               msg="An error Occured. Retry after some time.";
        }

        return msg;
    }

    @Override
    @Transactional
    public String editPlasticItemForDragDropGame(PlasticItem plasticItem, MultipartFile multipartPlasticImage) {
        String msg="";
        try {

            System.out.println(plasticItem.toString());
            String moduleImagePath="";
            PlasticItem plasticItem1=findPlasticItemById(plasticItem.getItemId());
            
            if(!multipartPlasticImage.isEmpty())
                moduleImagePath= s3BucketStorageService.uploadFormImage(multipartPlasticImage);
            else
                moduleImagePath=plasticItem1.getImage();
            
            plasticItem1.setImage(moduleImagePath);
            plasticItem1.setName(plasticItem.getName());
            plasticItem1.setPoint(Integer.parseInt(plasticItem.getPoint2()));
            if(plasticItem1.getGameId()==2)
            plasticItem1.setPlasticWeightPerUnit(plasticItem.getPlasticWeightPerUnit());
            else
                plasticItem1.setPlasticWeightPerUnit("0");

            if(addPlasticItem(plasticItem1)!=null)
                msg="Plastic Item  has been edited successfully.";

        }   catch (Exception ex){
            ex.printStackTrace();
            msg="An error Occured. Retry after some time.";
        }

        return msg;
    }


    @Override
    public String getAllPlasticItemByGame(int gameId) {



        Map<String, Object> templateModel = new HashMap();
        Context thymeleafContext = new Context();

        templateModel.put("dragDropPlasticItemList",findPlasticItemByGameId(gameId));
        templateModel.put("gameId",gameId);
        thymeleafContext.setVariables(templateModel);
        String   template="admin/game_plastic_item_listing.html";
       
        
        return  thymeleafTemplateEngine.process(template, thymeleafContext);
    }

    @Override
    public List<PlasticItem> findPlasticItemByGameId(int gameId) {
        return plasticItemRepository.findPlasticItemByGameId(gameId);
    }
}
