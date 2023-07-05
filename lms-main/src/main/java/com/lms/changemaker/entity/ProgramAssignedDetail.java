package com.lms.changemaker.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.List;

@Data
@NoArgsConstructor
public class ProgramAssignedDetail {

    private int progId;
    private String programName;
    private String  description;
    private String imagePath;
    private int studentId;
    private int schoolId;
    private int userId;
    private String startDate;
    private String endDate;
    private int limit=0;

    @Transient
    int remainProgram=0;

    @Transient
    double progress=0;

    @Transient
    List<ProgramAssignedDetail> programAssignedDetails;

    @Transient
    private String certifyUrl;

    @Transient
    int isOngoing=0;
    
    public ProgramAssignedDetail(int progId, String programName, String description, String imagePath, int studentId, int schoolId, int userId, String startDate, String endDate,int limit) {
        this.progId = progId;
        this.programName = programName;
        this.description = description;
        this.imagePath = imagePath;
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.limit = limit;
    }

    public ProgramAssignedDetail(int progId, String programName, String description, String imagePath, int studentId, int schoolId, int userId, String startDate, String endDate) {
        this.progId = progId;
        this.programName = programName;
        this.description = description;
        this.imagePath = imagePath;
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    
    }
}
