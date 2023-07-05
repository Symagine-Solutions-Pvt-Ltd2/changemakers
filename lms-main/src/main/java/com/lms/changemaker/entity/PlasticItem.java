package com.lms.changemaker.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name="plastic_item")
@Data
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class PlasticItem {


    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    @Column(name="item_id")
    private int itemId;


    private String name;

    private String image;
    

    @Column(name = "category_id")
    private int  categoryId;

    private int point;

    @Column(name = "game_id")
    private int gameId;


    @Column(name = "plastic_weight_per_unit")
    private String plasticWeightPerUnit;
    
     
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private PlasticCategory plasticCategory = new PlasticCategory();

    @Transient
    private String point2;
    


    public PlasticItem(String name, String image, int categoryId, int point, int gameId) {
        this.name = name;
        this.image = image;
        this.categoryId = categoryId;
        this.point = point;
        this.gameId = gameId;
    }
}
