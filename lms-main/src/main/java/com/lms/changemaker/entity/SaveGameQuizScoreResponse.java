package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;


@Data
@Slf4j
@NoArgsConstructor
public class SaveGameQuizScoreResponse implements Serializable  {


    private int studentId;
    private int taskIdQuiz;
    private int progress;
    private int progId;
    private int moduleId;
    private int score;


    
}
