package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


@Data
@Slf4j
@NoArgsConstructor
public class EditProgramAssignSubAdmin {


    @Transient
    public List<String> programAssigns=new ArrayList();

    public int schoolId;


    
    
}
