package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;


@Data
@Slf4j
@NoArgsConstructor
public class SaveGameScoreResponse implements Serializable  {

    private int correctCount=0;
    private List<Integer> correctItemId;
    private List<Integer> wrongItemId;
    private int moduleId;
    private static final String TAG = "SaveGameScoreResponse";

    
}
