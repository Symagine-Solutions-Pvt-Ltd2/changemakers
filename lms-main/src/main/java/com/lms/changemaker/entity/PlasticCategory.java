package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "plastic_category")
@Data
@NoArgsConstructor
public class PlasticCategory {
 



    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    @Column(name="cat_id")
    private int catId;

    @Column(name="short_name")
    private String shortName;
    
    private String category;
    private String image;

    @Column(name="game_id")
    private int gameId;
    
}
