package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


@Data
@Slf4j
@NoArgsConstructor
public class GetAssignedProgramsRequest implements Serializable  {


    private int studentId;
    private int schoolId;

}
